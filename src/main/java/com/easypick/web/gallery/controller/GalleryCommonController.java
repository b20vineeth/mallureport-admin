package com.easypick.web.gallery.controller;
  
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.easypick.admin.vo.CinemaGalleryVo;
import com.easypick.admin.vo.GalleryContentVo;
import com.easypick.admin.vo.GalleryVo;
import com.easypick.admin.vo.UploadFile;
import com.easypick.admin.vo.UploadedFile;
import com.easypick.framework.utility.commonUtility.GalleryXmlParse;
import com.easypick.framework.utility.commonUtility.StringUitity;
import com.easypick.framework.utility.exception.BussinessException;
import com.easypick.framework.utility.vo.ResponseVo;
import com.easypick.web.gallery.bussinesscontroller.GalleryCommonBussinessInterface;
import com.google.gson.Gson;
import com.sprhib.init.Initializer; 

@Controller
public class GalleryCommonController {

	Gson gson;
	 
	
	@Autowired
	protected HttpServletResponse response;
	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpSession session;
	@Autowired
	private HttpSession httpSession;  


	@Autowired
	protected GalleryCommonBussinessInterface galleryController;
	
  
	

	
	@RequestMapping(value = "/admin.gallery.create")
	@ResponseBody
	public ModelAndView galleryCreate(ModelMap modelMap) throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
			return new ModelAndView("Admin/user/CreateGallery");
		}
	}
	
	@RequestMapping(value = "/admin.gallery.upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(@RequestParam("uploadfile") MultipartFile uploadfiles) {
		return null;
		
		
	}
	

	@RequestMapping(value = "/admin.gallery.uploads", method = RequestMethod.POST)
	@ResponseBody
	public String uploadFile(@RequestParam("uploadfile") MultipartFile[] uploadfiles) {
		List<String> filenames = new ArrayList<>();
		String fileName = null;
		Random rand;
		String monthDate[] = StringUitity.generateUploadPath().split("-");

		String directory = Initializer.UPLOAD_PATH + "\\images\\" + monthDate[0] + "\\" + monthDate[1] + "\\";
		List<UploadFile> uploadFiles = new ArrayList<>();
		UploadFile uploadFile = null;
		for (MultipartFile uploadfile : uploadfiles) {
			uploadFile = new UploadFile();
			rand = new Random();
			int random = rand.nextInt(1000);
			fileName = random + "_" + uploadfile.getOriginalFilename();
			try {

				File file = new File(directory, fileName);
				uploadfile.transferTo(file);
				uploadFile.setFileName(monthDate[0] + "/" + monthDate[1] + "/" + fileName);
				uploadFile.setMonth(monthDate[1]);
				uploadFile.setYear(monthDate[0]);
				BufferedImage bimg = ImageIO.read(new File(directory, fileName));
				int width = bimg.getWidth(); // 100
				int height = bimg.getHeight(); // 250
				double imgWidth = 0.0;
				if (height > 100) {
					double wdHeight = height / 100;
					height = 100;
					imgWidth = width / wdHeight;
				}
				int roundWidth = (int) Math.round(imgWidth);

				BufferedImage img = new BufferedImage(roundWidth, 100, BufferedImage.TYPE_INT_RGB);

				img.createGraphics().drawImage(bimg.getScaledInstance(roundWidth, 100, Image.SCALE_SMOOTH), 0, 0, null);
				ImageIO.write(img, "jpg", new File(directory + "//thumb_" + fileName));
				uploadFile.setPath(
						"http://localhost:8081/pic/" + monthDate[0] + "/" + monthDate[1] + "/thumb_" + fileName);
				uploadFiles.add(uploadFile);
			} catch (IOException e) {
				System.out.print("ERROR");

			}

		}
		gson = new Gson();
		fileName = gson.toJson(uploadFiles);

		return fileName;
	}

	@RequestMapping(value = "/admin.gallery.uploadThumbnail", method = RequestMethod.POST)
	@ResponseBody
	public String uploadThumbnail(@RequestParam("uploadfile") MultipartFile[] uploadfiles,
			@RequestParam("id") String id) {
		List<String> filenames = new ArrayList<>();
		String fileName = null;
		Random rand;
		String monthDate[] = StringUitity.generateUploadPath().split("-");

		String directory = Initializer.UPLOAD_PATH + "\\images\\" + monthDate[0] + "\\" + monthDate[1] + "\\";
		List<UploadFile> uploadFiles = new ArrayList<>();
		UploadFile uploadFile = null;
		for (MultipartFile uploadfile : uploadfiles) {
			uploadFile = new UploadFile();
			rand = new Random();
			int random = rand.nextInt(1000);
			fileName = random + "_" + uploadfile.getOriginalFilename();
			try {

				File file = new File(directory, fileName);
				uploadfile.transferTo(file);
				fileName = monthDate[0] + "/" + monthDate[1] + "/" + fileName;

				try {
					ResponseVo response = galleryController.updateThumbnail(fileName, Integer.parseInt(id));
				} catch (NumberFormatException e) {

					e.printStackTrace();
				} catch (BussinessException e) {

					e.printStackTrace();
				}
			} catch (IOException e) {
				System.out.print("ERROR");

			}

		}

		return fileName;
	}

	@RequestMapping(value = "/admin.gallery.save", method = RequestMethod.POST)
	@ResponseBody
	public String gallerySave(ModelMap modelMap, @RequestBody @RequestParam("data") String data)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");

		} else {
			gson = new Gson();
			GalleryVo vo = gson.fromJson(data, GalleryVo.class);
			
			try {
				List<GalleryContentVo> content=GalleryXmlParse.parse(vo.getGalleryContent());
				vo.setContent(content);
				ResponseVo response = galleryController.saveGalleryVo(vo);
				return "T";
			} catch (Exception e) {
				return "F";
			}

		}
		return "Y";
	}

	@RequestMapping(value = "/admin.gallery.view", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView galleryView(ModelMap modelMap,
			@RequestBody @RequestParam(value = "tag", required = false) String tag,
			@RequestBody @RequestParam(value = "title", required = false) String title,
			@RequestBody @RequestParam(value = "page", required = false) String page) {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {

			GalleryVo vo = new GalleryVo();
			if (Objects.isNull(page) || page.trim().length() == 0)
				page = "1";
			vo.setPage(page);
			vo.setTag(tag);
			vo.setTitle(title);

			ResponseVo response = new ResponseVo();
			try {
				response = galleryController.getGalleryList(vo);

				modelMap.put("title", title);
				modelMap.put("tag", tag);
				modelMap.put("page", page);

				if (Objects.nonNull(response.getObjectList())) {
					modelMap.put("response", response);

				}

			} catch (BussinessException e) {

			}
			return new ModelAndView("Admin/user/ViewGallery");
		}
	}

	@RequestMapping(value = "/admin.gallery.edit", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView galleryEdit(ModelMap modelMap,
			@RequestBody @RequestParam(value = "galleryId", required = false) String galleryId)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
			ResponseVo response = null;
			GalleryVo galleryVo = new GalleryVo();

			galleryVo.setPerPage(200);
			try {
				galleryVo.setGalleryId(Integer.parseInt(galleryId));
			} catch (Exception e) {

			}
			response = new ResponseVo();
			response = galleryController.getGallery(galleryVo);

			if (Objects.nonNull(response.getObject())) {
				modelMap.put("response", response);

			}
			return new ModelAndView("Admin/user/CreateGallery");
		}
	}

	@RequestMapping(value = "/admin.gallery.updateThumbnail", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView updateThumbnail(ModelMap modelMap,
			@RequestBody @RequestParam(value = "galleryId", required = false) String galleryId)
			throws BussinessException {

		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
			ResponseVo response = null;
			GalleryVo galleryVo = new GalleryVo();

			galleryVo.setPerPage(200);
			try {
				galleryVo.setGalleryId(Integer.parseInt(galleryId));
			} catch (Exception e) {

			}
			response = new ResponseVo();
			response = galleryController.getGallery(galleryVo);

			if (Objects.nonNull(response.getObject())) {
				modelMap.put("response", response);

			}
			return new ModelAndView("Admin/user/UpdateThumbnail");
		}
	}
	
	
	
	

	@RequestMapping(value = "/admin.common.imageUpload", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView imageUpload(ModelMap modelMap,
			@RequestBody @RequestParam(value = "movieId", required = false) String movieId,
			@RequestBody @RequestParam(value = "imgsize", required = false) String imgsize) {
		String username = (String) httpSession.getAttribute("authKey");
		if (Objects.isNull(username)) {
			modelMap.put("redirectUrl", "login");
			return new ModelAndView("Admin/Redirect");
		} else {
		 	if (Objects.nonNull(imgsize)) {
				 
				modelMap.put("imgsize", imgsize);
				modelMap.put("movieId", movieId);
			}
			return new ModelAndView("Admin/popup/FileUpload");
		}

	}

	@RequestMapping(value = "/admin.common.saveimageUpload", method = RequestMethod.POST)
	@ResponseBody
	public String saveimageUpload(@ModelAttribute UploadedFile uploadedFile,
			@RequestPart(value = "files", required = false) MultipartFile file) {
		Random rand;
		String monthDate[] = StringUitity.generateUploadPath().split("-");
		String directory = Initializer.UPLOAD_PATH + "\\images\\" + monthDate[0] + "\\" + monthDate[1] + "\\";
		String filename=null;
			rand = new Random();
			int random = rand.nextInt(1000); 
			try {

				BufferedImage img = ImageIO.read(file.getInputStream());
				BufferedImage subimage = img.getSubimage(uploadedFile.getX1(), uploadedFile.getY1(),
						uploadedFile.getW(), uploadedFile.getH());
				ImageIO.write(subimage, "jpg", new File(directory+random+"_"+file.getOriginalFilename()));
				filename=monthDate[0] + "/" + monthDate[1] + "/"+random+"_"+file.getOriginalFilename();
				CinemaGalleryVo vo=new CinemaGalleryVo();
				vo.setMovid(uploadedFile.getMovid());
				if("335X180".equals(uploadedFile.getWh()))
				{
					vo.setThumb1(filename);
				}
				else if("690X390".equals(uploadedFile.getWh()))
				{
					vo.setThumb2(filename);
				}
				ResponseVo response = galleryController.saveCineGallery(vo);
				
			}
			catch(Exception e){
				return "F";
			}
			return "T";
				 
	}
	
	 
	
}
