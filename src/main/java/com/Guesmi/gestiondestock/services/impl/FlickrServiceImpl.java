package com.Guesmi.gestiondestock.services.impl;


import java.io.InputStream;

import com.Guesmi.gestiondestock.services.FlickrService;
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.uploader.UploadMetaData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FlickrServiceImpl implements FlickrService {

  @Value("${flickr.apiKey}")
  private String apiKey;

  @Value("${flickr.apiSecret}")
  private String apiSecret;

  @Value("${flickr.appKey}")
  private String appKey;

  @Value("${flickr.appSecret}")
  private String appSecret;
@Autowired
private Flickr flickr;

  public FlickrServiceImpl(Flickr flickr){
    this.flickr=flickr;
  }


  @Override

  public String savePhoto(InputStream photo, String title) throws FlickrException {

    UploadMetaData uploadMetaData = new UploadMetaData();
    uploadMetaData.setTitle(title);

    String photoId = flickr.getUploader().upload(photo, uploadMetaData);
    return flickr.getPhotosInterface().getPhoto(photoId).getMedium640Url();
  }



}
