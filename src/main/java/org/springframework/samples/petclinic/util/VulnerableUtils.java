package org.springframework.samples.petclinic.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class VulnerableUtils {

    // ⚠ Hardcoded credentials (Trivy/Snyk will detect)
    public static final String DB_PASSWORD = "supersecret123";

    public void runUnsafeQuery(String userInput) throws Exception {

        // ⚠ SQL Injection (Trivy/Snyk will detect)
        String sql = "SELECT * FROM owners WHERE last_name = '" + userInput + "'";

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/petclinic", "root", DB_PASSWORD);
        Statement stmt = conn.createStatement();
        
        stmt.executeQuery(sql);

        stmt.close();
        conn.close();
    }

    // ⚠ Insecure cryptography
    public String weakHash(String input) throws Exception {
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5"); // vulnerable
        byte[] hash = md.digest(input.getBytes());
        return new String(hash);
    }
}
