package com.cz.result.com.cz.result.getter;

import com.google.gson.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lijie on 2017/2/13.
 * Modified by YE on 2017/3/20.
 */
public class ReadStageJSON {

    private List<Stages> stagesList = new ArrayList<Stages>();
    private String stageUrl;


    public void setStageUrl(String a) {

        stageUrl ="http://133.133.10.1:18080/api/v1/applications/"+a+"/stages";

    }

    public String getStageUrl() {
        return stageUrl;
    }

    public void stagewsu() throws IOException {

       System.out.println(getStageUrl());
        URL url = new URL(getStageUrl());
    //    URL url = new URL( getStageUrl());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        //  connection.setConnectTimeout(8000);
        //  connection.setReadTimeout(8000);
        String line =null;
        String b ="";
        StringBuilder response = new StringBuilder();
        try {
            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            // StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                // b+=line;
                //   System.out.println(line);
                response.append(line).append("\r\n");
                // System.out.println(b);
                continue;
            }
            reader.close();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        String s = response.toString();
        System.out.println(s);

        try {
                JsonParser parser = new JsonParser();  //创建JSON解析器
                JsonElement el = parser.parse(s);
                JsonArray array = null;
                if (el.isJsonArray()) {
                    array = el.getAsJsonArray();
                }

                for (int i = 0; i < array.size(); i++) {

                    System.out.println("---------------");
                    JsonObject subObject = array.get(i).getAsJsonObject();
                    // JsonArray subArray = subObject.get("stageIds").getAsJsonArray();
                    Stages stage = new Stages();
                    stage.setStageId(subObject.get("stageId").getAsString());
                    stage.setName(subObject.get("name").getAsString());
                    stage.setAttemptId(subObject.get("attemptId").getAsString());
               //     System.out.println(stage.getAttemptId());
                    stage.setNumCompleteTasks(subObject.get("numCompleteTasks").getAsString());
                    stage.setTaskUrl(getStageUrl()+"/"+stage.getStageId()+"/"+stage.getAttemptId()+"/taskList?length="+stage.getNumCompleteTasks());
                    stage.setStatus(subObject.get("status").getAsString());
                    stage.setSubmitTime(subObject.get("submissionTime").getAsString());
                    stage.setCompleteTime(subObject.get("completionTime").getAsString());

                    double sh;
                    double sm;
                    double ss;
                    double sd;
                    double ch;
                    double cm;
                    double cs;
                    double cd;

                    sh= Integer.parseInt(stage.getSubmitTime().substring(11,13));
                    sm = Integer.parseInt(stage.getSubmitTime().substring(14,16));
                    ss = Integer.parseInt(stage.getSubmitTime().substring(17,19));
                    sd= Integer.parseInt(stage.getSubmitTime().substring(20,23));

                    ch= Integer.parseInt(stage.getCompleteTime().substring(11,13));
                    cm = Integer.parseInt(stage.getCompleteTime().substring(14,16));
                    cs = Integer.parseInt(stage.getCompleteTime().substring(17,19));
                    cd = Integer.parseInt(stage.getCompleteTime().substring(20,23));

                    double duration =(ch-sh)*3600+(cm-sm)*60+(cs-ss)+(cd-sd)/1000;
                    //System.out.println(ch);
                   // System.out.println(sh);
                    stage.setDuration(String.valueOf(duration));

                    stagesList.add(stage);

                }
              } catch (JsonIOException e) {
                e.printStackTrace();
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }

        }
        public List<Stages> getStagesList () {
        return stagesList;
    }
}