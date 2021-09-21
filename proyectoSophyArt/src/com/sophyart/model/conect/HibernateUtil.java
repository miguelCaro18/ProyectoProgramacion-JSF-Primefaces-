package com.sophyart.model.conect;


import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

 public class HibernateUtil {
	   private static SessionFactory sessionFactory = buildSessionFactory();
	   private static ServiceRegistry serviceRegistry = null;
	   
	   private static SessionFactory buildSessionFactory()
	   {
	      try
	      {
	         if (sessionFactory == null)
	         {
	            serviceRegistry = new StandardServiceRegistryBuilder()
	                    .configure("hibernate.cfg.xml")
	                    .build();

	            sessionFactory = new MetadataSources( serviceRegistry )
	                        .buildMetadata()
	                        .buildSessionFactory();
	        }
	        return sessionFactory;
	      } catch (Throwable ex)
	      {
	    	  
	         System.err.println("Initial SessionFactory creation failed." + ex);
	         throw new ExceptionInInitializerError(ex);
	      }
	   }
	 
	   public static SessionFactory getSessionFactory()
	   {
	      return sessionFactory;
	   }
	 
	   public static void shutdown()
	   {
	      getSessionFactory().close();
	   }
	}