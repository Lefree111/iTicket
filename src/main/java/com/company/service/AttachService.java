package com.company.service;


import com.company.dto.attach.AttachDTO;
import com.company.entity.AttachEntity;
import com.company.exc.AppBadRequestException;
import com.company.exc.ItemNotFoundException;
import com.company.repository.AttachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
public class AttachService {
    @Autowired
    private AttachRepository attachRepository;
    @Value("${attach.upload.folder}")
    private String attachFolder;
    @Value("${server.domain.name}")
    private String domainName;

    public AttachDTO upload(MultipartFile file) {
        String pathFolder = getYmDString(); // 2022/04/23
        File folder = new File(attachFolder + pathFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String key = UUID.randomUUID().toString(); // dasdasd-dasdasda-asdasda-asdasd
        String extension = getExtension(file.getOriginalFilename()); // dasda.asdas.dasd.jpg

        AttachEntity entity = saveAttach(key, pathFolder, extension, file);
        AttachDTO dto = toDTO(entity);

        try {// uploads/2022/04/23/dasdasd-dasdasda-asdasda-asdasd.jpg
            byte[] bytes = file.getBytes();
            Path path = Paths.get(attachFolder + pathFolder + "/" + key + "." + extension);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dto;
    }

    public byte[] open(String key) {
        byte[] imageInByte;
        BufferedImage originalImage;
        try {
            AttachEntity entity = get(key);
            String path = entity.getPath() + "/" + key + "." + entity.getExtension();

            originalImage = ImageIO.read(new File(attachFolder + path));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "png", baos);

            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    public byte[] open_general(String key) {
        byte[] data;
        try {
            AttachEntity entity = get(key);
            String path = entity.getPath() + "/" + key + "." + entity.getExtension();
            Path file = Paths.get(attachFolder + path);
            data = Files.readAllBytes(file);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public ResponseEntity<Resource> download(String key) { // images.png
        try {
            AttachEntity entity = get(key);
            String path = entity.getPath() + "/" + key + "." + entity.getExtension();
            Path file = Paths.get(attachFolder + path);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + entity.getOriginName() + "\"")
                        .body(resource);

            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public String getYmDString() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DATE);

        return year + "/" + month + "/" + day; // 2022/04/23
    }

    public String getExtension(String fileName) { // mp3/jpg/npg/mp4.....
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastIndex + 1);
    }

    public AttachEntity saveAttach(String key, String pathFolder, String extension, MultipartFile file) {
        AttachEntity entity = new AttachEntity();
        entity.setId(key);
        entity.setPath(pathFolder);
        entity.setOriginName(file.getOriginalFilename());
        entity.setExtension(extension);
        entity.setSize(file.getSize());
        entity.setCreateDate(LocalDateTime.now());
        attachRepository.save(entity);
        return entity;
    }

    public AttachDTO toDTO(AttachEntity entity) {
        AttachDTO dto = new AttachDTO();
        dto.setId(entity.getId());
        dto.setCreateDate(entity.getCreateDate());
        dto.setOriginName(entity.getOriginName());
        dto.setPath(entity.getPath());
        dto.setUrl(domainName + "api/v1/attach/download/" + entity.getId());
        return dto;
    }

    public Boolean delete(String key) {
        AttachEntity entity = get(key);

        File file = new File(attachFolder + entity.getPath() +
                "/" + entity.getId() + "." + entity.getExtension());

        if (file.delete()) {
            attachRepository.deleteById(key);
            return true;
        } else
            throw new AppBadRequestException("Could not read the file!");
    }

    public List<AttachDTO> paginationList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createDate"));

        List<AttachDTO> dtoList = new ArrayList<>();
        attachRepository.findAll(pageable).stream().forEach(entity -> {
            dtoList.add(toDTO(entity));
        });

        return dtoList;
    }

    public AttachEntity get(String id) {
        return attachRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Attach not found");
        });
    }

}