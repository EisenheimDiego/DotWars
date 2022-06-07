package ac.ucr.b66958.proyecto.persistence;

import ac.ucr.b66958.proyecto.domain.Memento;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.nio.file.Files;
import java.util.stream.Collectors;

public class FilePersistence {

    private String path;

    public FilePersistence(String path) {
        this.path = path;
    }

    public void saveFile(Memento memento){
        File file = new File(path);
        try {
            storeFile(memento, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Memento readFile(){
        File file = new File(path);
        return convertToEntity(file);
    }

    public void storeFile(Memento value, File file) throws IOException {
        String valueConverted = convert(value);

        BufferedWriter writer = null;

        try{
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(valueConverted);
        }finally {
            writer.close();
        }
    }

    private String convert(Memento memento) {
        try {
            //THIS LINE FORMATS THE
            // JSON FILE WRAPPING LINES
            return mapper().writerWithDefaultPrettyPrinter().writeValueAsString(memento);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Memento convertToEntity(File file){
        //Read file
        //Convert it using specialized class
        BufferedReader reader = null;
        try {
            reader = Files.newBufferedReader(file.toPath());
            String content =  reader.lines()
                    .collect(Collectors.joining(System.lineSeparator()));
            return toEntity(content);
        }catch (IOException e){
            e.printStackTrace();
            //Throw exception
        }finally {
            if(reader != null){
                try{
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public Memento toEntity(String content) {
        try {
            return mapper().readValue(content, Memento.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private ObjectMapper mapper() {
        ObjectMapper mapper = new ObjectMapper();
        //To convert LocalDate
        mapper.registerModule(new JavaTimeModule());
        //To avoid failing when it doesn't find a property on a bean
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //To avoid it converting date to timestamps
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }
}
