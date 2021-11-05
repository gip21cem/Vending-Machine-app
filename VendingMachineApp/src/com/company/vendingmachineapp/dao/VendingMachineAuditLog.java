package com.company.vendingmachineapp.dao;

// this interface must be implemented by the class
// that wants to write entries in the audit file
public interface VendingMachineAuditLog {
    public void auditLogWriter(String auditEntry);
}
