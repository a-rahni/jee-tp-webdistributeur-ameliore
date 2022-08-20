package fr.m2i.webdistributeur.listener;

import fr.m2i.webdistributeur.Distributor;
import fr.m2i.webdistributeur.dao.UserDao;
import fr.m2i.webdistributeur.entity.Role;
import fr.m2i.webdistributeur.utils.UserDb;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {

    private static Logger logger = Logger.getLogger(AppContextListener.class.getName());
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("---- App started ----");
        logger.info(dtf.format(LocalDateTime.now()));
        logger.info("---- App started ----");

        ServletContext ctx = sce.getServletContext();
        UserDb userDb = UserDb.getInstance(ctx.getInitParameter("dbUser"), ctx.getInitParameter("dbPass"));

        ctx.setAttribute("userDb", userDb);
        ctx.setAttribute("distrib", Distributor.getInstance());
        ctx.setAttribute("roles", Role.values());
        ctx.setAttribute("userDao", new UserDao(userDb));

        logger.info("---- Init done ----");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("---- App stopped ----");
        logger.info(dtf.format(LocalDateTime.now()));
        logger.info("---- App stopped ----");
    }
}
