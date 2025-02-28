package com.contactmanager.service;

import com.contactmanager.dto.CreateContactRequest;
import com.contactmanager.dto.UpdateContactRequest;
import com.contactmanager.entity.ContactEntity;
import com.contactmanager.repository.ContactRepository;

public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public void createContact(CreateContactRequest request) {
        // Check if the contact already exists
        ContactEntity existingContact = contactRepository.findByPhoneNumber(request.getPhoneNumber());
        if (existingContact != null) {
            throw new RuntimeException("Contact with this phone number already exists.");
        }

        // Create a new contact
        ContactEntity newContact = new ContactEntity();
        newContact.setContactId(request.getPhoneNumber()); // Use phone number as ID for simplicity
        newContact.setPhoneNumber(request.getPhoneNumber());
        newContact.setName(request.getName());
        newContact.setEmail(request.getEmail());

        // Save the new contact
        contactRepository.save(newContact);
    }

    @Override
    public void updateContact(UpdateContactRequest request) {
        // Fetch the existing contact
        ContactEntity existingContact = contactRepository.findByPhoneNumber(request.getPhoneNumber());
        if (existingContact == null) {
            throw new RuntimeException("Contact not found.");
        }

        // Update the contact details
        existingContact.setName(request.getName());
        existingContact.setEmail(request.getEmail());

        // Save the updated contact
        contactRepository.save(existingContact);
    }

    @Override
    public ContactEntity fetchContactByPhoneNumber(String phoneNumber) {
        ContactEntity contact = contactRepository.findByPhoneNumber(phoneNumber);
        if (contact == null) {
            throw new RuntimeException("Contact not found.");
        }
        return contact;
    }

    @Override
    public boolean deleteContact(String contactId) {
        return contactRepository.deleteById(contactId);
    }
}
