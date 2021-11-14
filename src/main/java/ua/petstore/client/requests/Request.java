package ua.petstore.client.requests;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import ua.petstore.client.models.ApiModel;

import java.io.IOException;

public interface Request {
    default String sendPost(ApiModel apiModel, String url) throws IOException {
        StringEntity entity = new StringEntity(HttpService.getInstance().getGson().toJson(apiModel, apiModel.getClass()));
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("accept", "application/json");
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.setEntity(entity);

        return HttpService.getInstance().send(httpPost);
    }

    default String sendPut(ApiModel apiModel, String url) throws IOException {
        StringEntity entity = new StringEntity(HttpService.getInstance().getGson().toJson(apiModel, apiModel.getClass()));
        HttpPut httpPut = new HttpPut(url);
        httpPut.addHeader("accept", "application/json");
        httpPut.addHeader("Content-Type", "application/json");
        httpPut.setEntity(entity);

        return HttpService.getInstance().send(httpPut);
    }

    default String sendGet(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("accept", "application/json");

        return HttpService.getInstance().send(httpGet);
    }

    default String sendDelete(String url) throws IOException {
        HttpDelete httpDelete = new HttpDelete(url);
        return HttpService.getInstance().send(httpDelete);
    }
}
