package services;

import DAOclasses.AnnouncementsDAO;
import DAOclasses.UsersDAO;
import entities.Announcement;
import entities.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

public class AnnouncementManager {
    private final AnnouncementsDAO announcementsDAO;
    private final UsersDAO usersDAO;

    private AnnouncementManager(AnnouncementsDAO announcementsDAO, UsersDAO usersDAO) {
        this.announcementsDAO=announcementsDAO;
        this.usersDAO=usersDAO;
    }

    public static AnnouncementManager getInstance(HttpSession session){
        AnnouncementManager announcementManager = (AnnouncementManager) session.getAttribute(AnnouncementManager.class.getSimpleName());
        if(announcementManager==null){
            announcementManager = new AnnouncementManager(AnnouncementsDAO.getInstance(session), UsersDAO.getInstance(session));
            session.setAttribute(AnnouncementManager.class.getSimpleName(), announcementManager);
        }
        return announcementManager;
    }


    /**
     * returns true if the announcement was successfully posted
     * (returns false if givenn userId did not have admin privileges
     * and the post could not be posted due to that)
     * @param userId
     * @param content
     * @return
     */
    //todo check: am doneze unda ganixilebodes sheedzleba tu ara user-s ramis dapostva?
    //tu frontis doneze ubralod tu admini xar post announcement gilaki gqondes
    //da sxvebs ar qondes?
    public boolean postAnnouncement(long userId, String content){
        //first u need to check if this user can post Announcement at all
        boolean successfullyPosted=false;
        if(usersDAO.isAdmin(userId)){
            announcementsDAO.addAnnouncement(userId, content);
            successfullyPosted=true;
        }
        //posting announcement means adding it to the database
        return successfullyPosted;
    }


}
