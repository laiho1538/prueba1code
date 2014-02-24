package org.upiita.spring.jdbc.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.upiita.spring.jdbc.entidades.Video;

@Component("videoDAO")
public class VideoDAOHibernate implements VideoDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void creaVideo(Video video) {

		Session sesion = sessionFactory.openSession();

		sesion.beginTransaction();
		sesion.save(video);
		sesion.getTransaction().commit();
		sesion.close();

	}

	public Video obtenVideoPorId(Integer videoId) {
		Session sesion = sessionFactory.openSession();

		sesion.beginTransaction();
		Video video = (Video) sesion.get(Video.class, videoId);
		sesion.getTransaction().commit();
		sesion.close();

		return video;
	}

	public List<Video> buscaVideoPorTitulo(String titulo) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Video.class);
		criteria.add(Restrictions.like("titulo", "%" + titulo + "%"));
		List<Video> listaVideos = criteria.list();
		return listaVideos;
	}

	public Video buscaVideoPorDescripcion(String descripcion) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Video.class);
		criteria.add(Restrictions.eq("descripcion", descripcion));
		Video video = (Video)criteria.uniqueResult();
		return video;
	}

}
