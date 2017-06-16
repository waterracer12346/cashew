/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectclasses;

/**
 *
 * @author 068042449
 */
public class Question {

    private String userID;
    private String date;
    private String description;
    private boolean active;
    private int priority;
    
    /**
     *
     * @param id    User id (Student Username)
     * @param dt    Timestamp of question
     * @param des   Description of question
     * @param a     Boolean for whether question is active
     * @param p     Priority of question
     */
    public Question(String id, String dt, String des, boolean a, int p){
        userID=id;
        date =dt;
        description=des;
        active=a;
        priority=p;
    }
    
    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    

}
