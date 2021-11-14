package ua.petstore.client.requests;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;
import ua.petstore.client.models.Pet;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PetRequests implements Request {

    private static PetRequests instance;
    private static final String URL_uploadImage = "/pet/{petId}/uploadImage";
    private static final String URL_pet = "/pet";
    private static final String URL_findByStatus = "/pet/findByStatus";
    private static final String URL_findById = "/pet/{petId}";
    private static final String URL_updateWithForm = "/pet/{petId}";
    private static final String URL_deletePet = "/pet/{petId}";

    public static final String PETSTATUS_AVAILABLE = "available";
    public static final String PETSTATUS_PENDING = "pending";
    public static final String PETSTATUS_SOLD = "sold";

    public static PetRequests getInstance() {
        if (instance == null) {
            instance = new PetRequests();
        }
        return instance;
    }

    public String newPet(Pet pet) throws IOException {
        return sendPost(pet, HttpService.HOST + URL_pet);
    }

    public String updatePet(Pet pet) throws IOException {
        return sendPut(pet, HttpService.HOST + URL_pet);
    }

    public String findByStatus(String[] statuses) throws IOException {
        StringBuilder url = new StringBuilder(HttpService.HOST + URL_findByStatus + "?");
        for (String status : statuses) {
            url.append("status=").append(status).append("&");
        }
        return sendGet(url.toString());
    }

    public String findById(Pet pet) throws IOException {
        return sendGet(HttpService.HOST + URL_findById.replaceAll("\\{petId}", pet.getId().toString()));
    }

    public String deleteById(Pet pet) throws IOException {
        return sendDelete(HttpService.HOST + URL_deletePet.replaceAll("\\{petId}", pet.getId().toString()));
    }

    public String uploadImage(Pet pet, String additionalMetadata, String filePath) throws IOException {
        File file = new File(filePath);
        String responseJson = "";
        if (file.exists() && file.isFile()) {
            FileBody fileBody = new FileBody(file, ContentType.DEFAULT_BINARY);
            StringBody stringBody = new StringBody(additionalMetadata, ContentType.MULTIPART_FORM_DATA);

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.addPart("file", fileBody);
            builder.addPart("additionalMetadata", stringBody);
            HttpEntity entity = builder.build();

            HttpPost httpPost = new HttpPost(HttpService.HOST + URL_uploadImage.replaceAll("\\{petId}", pet.getId().toString()));
            httpPost.addHeader("accept", "application/json");
            httpPost.setEntity(entity);
            responseJson = HttpService.getInstance().send(httpPost);
        }
        return responseJson;
    }

    public String updatePetWithForm(Pet pet) throws IOException {

        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("name", pet.getName()));
        list.add(new BasicNameValuePair("status", pet.getStatus()));
        HttpEntity entity = new UrlEncodedFormEntity(list, StandardCharsets.UTF_8);

        HttpPost httpPost = new HttpPost(HttpService.HOST + URL_updateWithForm.replaceAll("\\{petId}", pet.getId().toString()));
        httpPost.addHeader("accept", "application/json");
        //httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.setEntity(entity);
        return HttpService.getInstance().send(httpPost);
    }
}