package com.easypick.admin.admin.validation;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.easypick.admin.vo.GallerySetupVo;

public class GalleryXmlParse {
	
	List<GallerySetupVo> parse(GallerySetupVo gallerySetupVo) {
		DocumentBuilder documentBuilder = null;
		try {
			GallerySetupVo gallerySetup=null;
			List<GallerySetupVo> gallerySetupVos=new ArrayList<>();
			documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource inputSource = new InputSource();
			inputSource.setCharacterStream(new StringReader(gallerySetupVo.getPicUrl()));
			Document doc = documentBuilder.parse(inputSource);
			Element element = doc.getDocumentElement();
			NodeList nodeList = doc.getDocumentElement().getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					gallerySetup=new GallerySetupVo();
					Element elem = (Element) node;
					updateThumbUrl(gallerySetupVo, gallerySetup, elem);
					updatePicUrl(gallerySetupVo, gallerySetup, elem);
					updateTitle(gallerySetupVo, gallerySetup, elem);
					updateDescription(gallerySetupVo, gallerySetup, elem);
					updatePageUrl(gallerySetupVo, gallerySetup, elem,i);
					updateMetaDesc(gallerySetupVo, gallerySetup, elem);
					updateTag(gallerySetupVo, gallerySetup, elem);
					gallerySetupVos.add(gallerySetup);
				}
			}
			return gallerySetupVos;

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void updateTag(GallerySetupVo gallerySetupVo, GallerySetupVo gallerySetup, Element elem) {
		try{
			 gallerySetup.setTag(elem.getElementsByTagName("tag").item(0).getChildNodes().item(0).getNodeValue());
		}catch(Exception e)
		{
			gallerySetup.setTag(gallerySetupVo.getTag());
		}
	}

	private void updateDescription(GallerySetupVo gallerySetupVo, GallerySetupVo gallerySetup, Element elem) {

		try{
			 gallerySetup.setDescription(elem.getElementsByTagName("desc").item(0).getChildNodes().item(0).getNodeValue());
		}catch(Exception e)
		{
			gallerySetup.setDescription(gallerySetupVo.getDescription());
		}
	}
	

	private void updateMetaDesc(GallerySetupVo gallerySetupVo, GallerySetupVo gallerySetup, Element elem) {

		try{
			 gallerySetup.setMetaData(elem.getElementsByTagName("meta").item(0).getChildNodes().item(0).getNodeValue());
		}catch(Exception e)
		{
			gallerySetup.setMetaData(gallerySetupVo.getMetaData());
		}
	}
	private void updatePageUrl(GallerySetupVo gallerySetupVo, GallerySetupVo gallerySetup, Element elem, int i) {

		try{
			 gallerySetup.setPageUrl(elem.getElementsByTagName("pageUrl").item(0).getChildNodes().item(0).getNodeValue());
		}catch(Exception e)
		{
			gallerySetup.setPageUrl(gallerySetupVo.getPageUrl()+"-"+i);
		}
	}

	private void updateTitle(GallerySetupVo gallerySetupVo, GallerySetupVo gallerySetup, Element elem) {

		try{
			 gallerySetup.setTitle(elem.getElementsByTagName("title").item(0).getChildNodes().item(0).getNodeValue());
		}catch(Exception e)
		{
			gallerySetup.setTitle(gallerySetupVo.getTitle());
		}
	}

	private void updateThumbUrl(GallerySetupVo gallerySetupVo, GallerySetupVo gallerySetup, Element elem) {
		try{
			 gallerySetup.setThumbUrl(elem.getElementsByTagName("thumb").item(0).getChildNodes().item(0).getNodeValue());
		}catch(Exception e)
		{
			gallerySetup.setThumbUrl(gallerySetupVo.getThumbUrl());
		}
	}
	
	private void updatePicUrl(GallerySetupVo gallerySetupVo, GallerySetupVo gallerySetup, Element elem) {
		try{
			 gallerySetup.setPicUrl(elem.getElementsByTagName("picUrl").item(0).getChildNodes().item(0).getNodeValue());
		}catch(Exception e)
		{
			gallerySetup.setPicUrl(gallerySetupVo.getPicUrl());
		}
	}
	
	

}
