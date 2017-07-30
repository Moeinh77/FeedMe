package com.taan.hasani.moein.feedme;

import android.content.SharedPreferences;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by Moein on 7/28/2017.
 */

public class Parse_News {
    private final String TAG = "Parse_News";
    ArrayList<Item> itemsList;

    public Parse_News() {
        itemsList=new ArrayList<>();
    }

    public ArrayList<Item> getItemsList(){
        return itemsList;
    }

    public boolean parse(String xml){
        boolean status=true;
        Item current_item=null;
        String textvalue="";
        boolean inItem=false;

        try{
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xmlPullParser=factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xml));
            int eventType=xmlPullParser.getEventType();

            while(eventType!= XmlPullParser.END_DOCUMENT){
                String tagname = xmlPullParser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("item".equalsIgnoreCase(tagname)){
                        inItem=true;
                        current_item=new Item();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textvalue=xmlPullParser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if ("item".equalsIgnoreCase(tagname)) {
                            itemsList.add(current_item);
                            inItem = false;
                        } else if ("title".equalsIgnoreCase(tagname)) {
                            current_item.setTitle(textvalue);
                        } else if ("description".equalsIgnoreCase(tagname)) {
                            current_item.setDescription(textvalue);
                        }
                        break;
                }
                eventType = xmlPullParser.next();
            }
        }catch(Exception e){
            status=false;
            e.printStackTrace();
        }
        return status;
    }

}
