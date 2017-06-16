/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectclasses;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author 068042449
 */
public class User {

    private String username;
    private String password;
    private String fName;
    private String lName;
    private int userType;

    private MessageDigest md = null;

    public User(String user, String pass, String f, String l, int ut) {
        username = user;
        password = encrypt(pass);
        fName = f;
        lName = l;
        userType = ut;
    }

    public User(String user, String pass) {
        username = user;
        password = pass;
    }

    public String encrypt(String s) {
        try {
            setMd(MessageDigest.getInstance("SHA-256")); //Creating method digest instance with SHA-256 algorithm
            getMd().update(s.getBytes()); //adding bytes from passed String to messageDigest
            byte byteData[] = getMd().digest(); //Creating an array of bytes with messageDigest
            String ePass = ""; //Creating blank string for hexadecimal conversion
            for (int i = 0; i < byteData.length; i++) { //Converting bytes from message digest to hexadecimal and 
                ePass += (Integer.toHexString(byteData[i] & 0xFF | 0x100).substring(1, 3));
            }
            return ePass; //Return String as hexadeciomal format
        } catch (NoSuchAlgorithmException e) { //Catch if SHA256 alogirthm is not found
            System.err.println("I'm sorry, but the encryption algorithm is not a valid message digest algorithm");
            return null;
        }
    }

    public String toString() {
        return username + "," + password + "," + fName + "," + lName + "," + userType;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the fName
     */
    public String getfName() {
        return fName;
    }

    /**
     * @param fName the fName to set
     */
    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     * @return the lName
     */
    public String getlName() {
        return lName;
    }

    /**
     * @param lName the lName to set
     */
    public void setlName(String lName) {
        this.lName = lName;
    }

    /**
     * @return the userType
     */
    public int getUserType() {
        return userType;
    }

    /**
     * @param userType the userType to set
     */
    public void setUserType(int userType) {
        this.userType = userType;
    }

    /**
     * @return the md
     */
    public MessageDigest getMd() {
        return md;
    }

    /**
     * @param md the md to set
     */
    public void setMd(MessageDigest md) {
        this.md = md;
    }

}
