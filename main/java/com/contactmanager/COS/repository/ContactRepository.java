package com.contactmanager.repository;

import com.contactmanager.entity.ContactEntity;

public interface ContactRepository {
    ContactEntity save(ContactEntity contact);
    ContactEntity findByPhoneNumber(String phoneNumber);
    boolean deleteById(String contactId);
}
