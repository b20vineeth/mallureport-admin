package com.easypick.framework.utility.commonUtility;

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

import com.easypick.admin.vo.GalleryContentVo; 

public class GalleryXmlParse {
	
	public static List<GalleryContentVo> parse(String data) {
		DocumentBuilder documentBuilder = null;
		try {
			 List<GalleryContentVo> galleryContentVos=new ArrayList<>();
			documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource inputSource = new InputSource();
			inputSource.setCharacterStream(new StringReader(data));
			Document doc = documentBuilder.parse(inputSource); 
			NodeList nodeList = doc.getDocumentElement().getChildNodes();
			GalleryContentVo galleryContentVo=null;
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					galleryContentVo=new GalleryContentVo();
					Element elem = (Element) node;
					updateThumb1(galleryContentVo,elem); 
					updateThumb2(galleryContentVo,elem);
					updateThumb3(galleryContentVo,elem);
					galleryContentVos.add(galleryContentVo);
				}
			}
			return galleryContentVos;

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void updateThumb3(GalleryContentVo galleryContentVo, Element elem) {
		try{
			galleryContentVo.setThumb3(elem.getElementsByTagName("thumb3").item(0).getChildNodes().item(0).getNodeValue());
		}catch(Exception e)
		{
			galleryContentVo.setThumb2("");
		}
		
	}

	private static void updateThumb2(GalleryContentVo galleryContentVo, Element elem) {
		try{
			galleryContentVo.setThumb2(elem.getElementsByTagName("thumb2").item(0).getChildNodes().item(0).getNodeValue());
		}catch(Exception e)
		{
			galleryContentVo.setThumb2("");
		}
		
	}

	private static void updateThumb1(GalleryContentVo galleryContentVo, Element elem) {
		try{
			galleryContentVo.setThumb1(elem.getElementsByTagName("thumb1").item(0).getChildNodes().item(0).getNodeValue());
		}catch(Exception e)
		{
			galleryContentVo.setThumb1("");
		}
		
	}

	 
	

}
