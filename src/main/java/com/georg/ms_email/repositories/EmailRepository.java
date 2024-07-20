package com.georg.ms_email.repositories;

import com.georg.ms_email.models.Email;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface EmailRepository extends MongoRepository<Email, UUID> {
}
