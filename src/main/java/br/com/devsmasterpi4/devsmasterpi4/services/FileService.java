package br.com.devsmasterpi4.devsmasterpi4.services;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
public class FileService {

    private final String pathDirectory = "/home/astaroth/Downloads/";

    public ResponseEntity<?> getFile(String fileName, int id) {
        String pathImage = pathDirectory + id + "/" + fileName;
        System.out.println(pathImage);
        File file = new File(pathImage);
        if (file != null && file.exists()) {
            Path path = Paths.get(file.getAbsolutePath());
            ByteArrayResource resource = null;
            String typeMimeFile = "";
            try {
                resource = new ByteArrayResource(Files.readAllBytes(path));
                URLConnection connection = file.toURI().toURL().openConnection();
                typeMimeFile = connection.getContentType();
            } catch (IOException e) {
                //TODO tratar exception, informar console
                e.printStackTrace();
            }
            return ResponseEntity.ok()
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType(typeMimeFile))
                    .body(resource);
        } else {
            return ResponseEntity.badRequest().body("Error in get file, this file or directory is not exists!");
        }
    }

    public ResponseEntity<String> saveFile(MultipartFile file) {

        String fileName = file.getOriginalFilename();
        int id = 0; //o id aqui precisa ser o do produto cadastrado nesse momento no banco de dados para criarmos um diretorio com esse id
        new File(pathDirectory + id).mkdir();
        Path root = Paths.get(pathDirectory + id);
        try {
            Files.copy(file.getInputStream(), root.resolve(fileName), REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();//TODO TRATAR ERRO Error saving file
        }
        return ResponseEntity.ok().body("ok");
    }

    //TODO ESTE METODO VAI SER ALTERADO DEPOIS MAIS PARA FRENTE TALVEZ
    public void deleteDirectoryWithImages(int id) {
        String pathImage = pathDirectory + id;
        File f = new File(pathImage);
        if (f.exists()) {
            f.delete();
        }
    }


}
