package com.emiage.s12018.noteReminder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emiage.s12018.noteReminder.entity.Note;
import com.emiage.s12018.noteReminder.entity.Users;


public interface NoteRepository extends JpaRepository<Note, Long> {
	//Optional<Note> findByCode(String code);
	
	List<Note> findByUserIdUser(Long idUser);
}

