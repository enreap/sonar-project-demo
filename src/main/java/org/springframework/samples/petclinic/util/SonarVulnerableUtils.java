package org.springframework.samples.petclinic.util;

import java.io.FileInputStream;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SonarVulnerableUtils {

    // ❌ Hardcoded credentials — Sonar Security Hotspot (java:S2068)
    private static final String PASSWORD = "SuperSecretPassword123";

    public void runUnsafeSQL(String lastName) throws Exception {

        // ❌ SQL Injection Vulnerability — (java:S3649, java:S2077)
        String query = "SELECT * FROM owners WHERE last_name = '" + lastName + "'";

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/petclinic", "root", PASSWORD);
        Statement stmt = conn.createStatement();
        stmt.executeQuery(query);

        stmt.close();
        conn.close();
    }

    public String weakHash(String input) throws Exception {

        // ❌ Weak cryptography MD5 — Sonar Security Hotspot (java:S4790)
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(input.getBytes());
        return new String(hash);
    }

    public void riskyFileAccess(String filename) throws Exception {

        // ❌ Path traversal vulnerability — (java:S2083)
        FileInputStream fis = new FileInputStream("/var/data/" + filename);  
        fis.read();
        fis.close();
    }

    public void badExceptionHandling() {

        try {
            int x = 10/0;
        } catch (Exception e) {

            // ❌ Logging sensitive info — (java:S2228)
            System.out.println("Error happened: " + e.getMessage()); 
        }
    }
}
