package org.upiita.spring.jdbc.tests;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.upiita.spring.jdbc.daos.VideoDAO;
import org.upiita.spring.jdbc.entidades.Video;

public class TestEjercicio {

	public static void main(String[] args) {
		ApplicationContext contexto = new ClassPathXmlApplicationContext("/contexto.xml");

		VideoDAO videoDAO = (VideoDAO) contexto.getBean("videoDAO");
		Video video = new Video();
		video.setDescripcion("descripcion video");
		video.setTitulo("titulo video");
		video.setUrlVideo("url video");
		video.setVideoId(4);

		// creamos el video
		videoDAO.creaVideo(video);

		// buscamos el video en la base
		Video videoDesdeBD = videoDAO.obtenVideoPorId(video.getVideoId());

		System.out.println("el video de la base es:");
		System.out.println(videoDesdeBD);
		
		//buscamos por titulo y oluego por descripcion
		//LIKE
		List<Video> videos = videoDAO.buscaVideoPorTitulo("tit");
		System.out.println("esta es la lista de videos " + videos);
		
		//EQUALS
		Video videoBD = videoDAO.buscaVideoPorDescripcion(" descripcion 1, bla bla bla");
		System.out.println("este es el video con la descripcion que mandaste " + videoBD);

	}
}