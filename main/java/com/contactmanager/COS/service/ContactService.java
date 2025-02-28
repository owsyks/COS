package com.contactmanager.service;

import com.contactmanager.dto.CreateContactRequest;
import com.contactmanager.dto.UpdateContactRequest;
import com.contactmanager.entity.ContactEntity;

public interface ContactService {
    void createContact(CreateContactRequest request);
    void updateContact(UpdateContactRequest request);
    ContactEntity fetchContactByPhoneNumber(String phoneNumber);
    boolean deleteContact(String contactId);
}
