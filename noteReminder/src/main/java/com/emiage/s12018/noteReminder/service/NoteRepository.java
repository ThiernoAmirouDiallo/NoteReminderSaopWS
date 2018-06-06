package com.emiage.s12018.noteReminder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.emiage.s12018.noteReminder.entity.Note;
import com.emiage.s12018.noteReminder.entity.Users;


public interface NoteRepository extends JpaRepository<Note, Long> {
	//recuperer les notes d'un utilisateur triés par ordre
	List<Note> findByUserIdUserOrderByOrdre(Long idUser);
}

