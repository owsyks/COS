package com.contactmanager.repository;

import com.contactmanager.entity.ContactEntity;

import java.util.HashMap;
import java.util.Map;

public class InMemoryContactRepository implements ContactRepository {
    private final Map<String, ContactEntity> contactMap = new HashMap<>();

    @Override
    public ContactEntity save(ContactEntity contact) {
        contactMap.put(contact.getContactId(), contact);
        return contact;
    }

    @Override
    public ContactEntity findByPhoneNumber(String phoneNumber) {
        return contactMap.values().stream()
                .filter(contact -> phoneNumber.equals(contact.getPhoneNumber()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean deleteById(String contactId) {
        return contactMap.remove(contactId) != null;
    }
}
