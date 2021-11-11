package com.company.vendingmachineapp.dao;

import java.io.*;
import java.time.LocalDateTime;

@Component
public class VendingMachineAuditLogImpl implements VendingMachineAuditLog {

    private static final File auditLogFile = new File("F:\\Mthree\\VendingMachineApp\\VendingMachineAuditLog.txt");

    @Override
    public void auditLogWriter(String auditEntry) {
        // write file with append option true so that it will not be overwritten
        try(FileWriter output = new FileWriter(auditLogFile, true)) {
            BufferedWriter lineWriter = new BufferedWriter(output);

            LocalDateTime timestamp = LocalDateTime.now();

            lineWriter.write(timestamp.toString() + ": " + auditEntry + "\n");
            lineWriter.flush();
        } catch(IOException exc) {
            exc.printStackTrace();
        }
    }
}
