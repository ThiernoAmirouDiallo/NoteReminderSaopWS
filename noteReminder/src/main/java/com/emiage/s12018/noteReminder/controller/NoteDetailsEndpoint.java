package com.emiage.s12018.noteReminder.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.emiage.s12018.noteReminder.entity.Note;
import com.emiage.s12018.noteReminder.entity.Users;
import com.emiage.s12018.noteReminder.exception.ActionNotAuthorizedException;
import com.emiage.s12018.noteReminder.exception.NoteNotFoundException;
import com.emiage.s12018.noteReminder.exception.UserNameUnavailableException;
import com.emiage.s12018.noteReminder.service.NoteRepository;
import com.emiage.s12018.noteReminder.service.UserRepository;
import com.emiage2018s1.notes.AddNoteDetails;
import com.emiage2018s1.notes.AddNoteDetailsRequest;
import com.emiage2018s1.notes.AddNoteDetailsResponse;
import com.emiage2018s1.notes.AddUserDetailsRequest;
import com.emiage2018s1.notes.AddUserDetailsResponse;
import com.emiage2018s1.notes.DeleteNoteDetailsRequest;
import com.emiage2018s1.notes.DeleteNoteDetailsResponse;
import com.emiage2018s1.notes.EditNoteDetailsRequest;
import com.emiage2018s1.notes.EditNoteDetailsResponse;
import com.emiage2018s1.notes.GetAllNoteDetailsRequest;
import com.emiage2018s1.notes.GetAllNoteDetailsResponse;
import com.emiage2018s1.notes.GetNoteDetailsRequest;
import com.emiage2018s1.notes.GetNoteDetailsResponse;
import com.emiage2018s1.notes.NoteDetails;
import com.emiage2018s1.notes.Status;
import com.emiage2018s1.notes.UserDetails;

@Endpoint
public class NoteDetailsEndpoint {

	@Autowired
    public PasswordEncoder passwordEncoder;
    
	@Autowired
	NoteRepository noteService;

	@Autowired
	UserRepository userRepository;

	// method
	// input - GetNoteDetailsRequest
	// output - GetNoteDetailsResponse

	// http://emiage2018s1.com/notes
	// GetNoteDetailsRequest
	@PayloadRoot(namespace = "http://emiage2018s1.com/notes", localPart = "GetNoteDetailsRequest")
	@ResponsePayload
	public GetNoteDetailsResponse processNoteDetailsRequest(@RequestPayload GetNoteDetailsRequest request) {
		//vérification que le user a le droit (adduser ne doit que ajouter des utilisateurs et les les autres ne peuvent pas)
		verifieDroit(SecurityContextHolder.getContext().getAuthentication().getName(),true);
		
		
		Optional<Note> note = noteService.findById((long) request.getId());

		//si la note n'existe pas
		if (!note.isPresent())
			throw new NoteNotFoundException("Note non trouvée - Id " + request.getId());
		else {
			//vérification de l'appartenance de la note a l'utilisateur avant la suppression
			Users user= userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
			System.out.println(SecurityContextHolder.getContext().getAuthentication().getName()+" => "+user);
			System.out.println(note.get());
			//si la note n'appartient pas a l'utilisateur
			if (note.get().getUser().getIdUser()!=user.getIdUser())
				throw new ActionNotAuthorizedException("Vous n'avez pas accès à cette note - Id " + request.getId()	);
		}

		return mapNoteDetails(note.get());
	}

	//on verifie si le user connecté a droit de faire ce qu'il veut
	private void verifieDroit(String username, boolean estGestionDesNotes) {
		if(username.equals("adduser") && estGestionDesNotes)
			throw new ActionNotAuthorizedException("Le user '" +username + "' ne peut pas avoir accès aux notes"	);
		else if(!username.equals("adduser") && !estGestionDesNotes)
			throw new ActionNotAuthorizedException("Le user '" +username + "' ne peut pas ajouter des utilisateur"	);
	}

	private GetNoteDetailsResponse mapNoteDetails(Note note) {
		GetNoteDetailsResponse response = new GetNoteDetailsResponse();
		response.setNoteDetails(mapNote(note));
		return response;
	}
	
	private AddNoteDetailsResponse mapAddNoteDetails(Note note) {
		AddNoteDetailsResponse response = new AddNoteDetailsResponse();
		response.setNoteDetails(mapNote(note));
		return response;
	}
	
	private EditNoteDetailsResponse mapEditNoteDetails(Note note) {
		EditNoteDetailsResponse response = new EditNoteDetailsResponse();
		response.setNoteDetails(mapNote(note));
		return response;
	}

	private GetAllNoteDetailsResponse mapAllNoteDetails(List<Note> notes) {
		GetAllNoteDetailsResponse response = new GetAllNoteDetailsResponse();
		for (Note note : notes) {
			NoteDetails mapNote = mapNote(note);
			response.getNoteDetails().add(mapNote);
		}
		return response;
	}

	private NoteDetails mapNote(Note note) {
		NoteDetails noteDetails = new NoteDetails();

		noteDetails.setId(note.getIdNote().intValue());

		noteDetails.setTexte(note.getTexte());
		noteDetails.setCouleur(note.getCouleur());
		noteDetails.setEcheance(note.getEcheance());
		

		noteDetails.setOrdre((int) note.getOrdre());
		return noteDetails;
	}
	
	private Note mapNote(NoteDetails noteDetails) {
		Note note = new Note();

		note.setIdNote((long) noteDetails.getId());

		note.setTexte(noteDetails.getTexte());
		note.setCouleur(noteDetails.getCouleur());
		note.setEcheance(noteDetails.getEcheance());
		

		note.setOrdre((int) noteDetails.getOrdre());
		return note;
	}
	
	private Note mapNote(AddNoteDetails noteDetails) {
		Note note = new Note();

		note.setIdNote(null);

		note.setTexte(noteDetails.getTexte());
		note.setCouleur(noteDetails.getCouleur());
		note.setEcheance(noteDetails.getEcheance());
		

		note.setOrdre((int) noteDetails.getOrdre());
		return note;
	}

	@PayloadRoot(namespace = "http://emiage2018s1.com/notes", localPart = "GetAllNoteDetailsRequest")
	@ResponsePayload
	public GetAllNoteDetailsResponse processAllNoteDetailsRequest(
			@RequestPayload GetAllNoteDetailsRequest request) {

		//vérification que le user a le droit (adduser ne doit que ajouter des utilisateurs et les les autres ne peuvent pas)
				verifieDroit(SecurityContextHolder.getContext().getAuthentication().getName(),true);
		
		//System.out.println(SecurityContextHolder.getContext().getAuthentication().getName()+" => "+user);
		//recuperation des notes de l'utilisateur connecté
		Users user= userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		List<Note> notes = noteService.findByUserIdUserOrderByOrdre(user.getIdUser());
		
		return mapAllNoteDetails(notes);
	}

	@PayloadRoot(namespace = "http://emiage2018s1.com/notes", localPart = "DeleteNoteDetailsRequest")
	@ResponsePayload
	public DeleteNoteDetailsResponse deleteNoteDetailsRequest(@RequestPayload DeleteNoteDetailsRequest request) {
		//vérification que le user a le droit (adduser ne doit que ajouter des utilisateurs et les les autres ne peuvent pas)
		verifieDroit(SecurityContextHolder.getContext().getAuthentication().getName(),true);		

		Status status = Status.FAILURE;
		Optional<Note> note = noteService.findById((long) request.getId());
		
		//si la note n'existe pas
		if (!note.isPresent())
			throw new NoteNotFoundException("Note non trouvée - Id " + request.getId());
		else {
			//vérification de l'appartenance de la note a l'utilisateur avant la suppression
			Users user= userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
			System.out.println(SecurityContextHolder.getContext().getAuthentication().getName()+" => "+user);
			System.out.println(note.get());
			//si la note n'appartient pas a l'utilisateur
			if (note.get().getUser().getIdUser()!=user.getIdUser())
				throw new ActionNotAuthorizedException("Vous n'avez pas accès à cette note - Id " + request.getId());
		
			noteService.deleteById((long) request.getId());
			status = Status.SUCCESS;
		}
 		
		DeleteNoteDetailsResponse response = new DeleteNoteDetailsResponse();
		response.setStatus(mapStatus(status));
		return response;
	}
	
	@PayloadRoot(namespace = "http://emiage2018s1.com/notes", localPart = "AddUserDetailsRequest")
	@ResponsePayload
	public AddUserDetailsResponse addUserDetailsRequest(@RequestPayload AddUserDetailsRequest request) {
		//vérification que le user a le droit (adduser ne doit que ajouter des utilisateurs et les les autres ne peuvent pas)
		verifieDroit(SecurityContextHolder.getContext().getAuthentication().getName(),false);
				
		Status status = Status.FAILURE;
		Users userInput = mapUser(request.getUserDetails());
		
		//vérification de l'unicité du user name
		Users user = userRepository.findByUsername(userInput.getUsername());
		if (user != null) 
			throw new UserNameUnavailableException("Ce nom d'utilisateur est déja utilisé - Username " + userInput.getUsername());
		
		userRepository.save(userInput);
		
		//Succès ajout
		status = Status.SUCCESS;
		
		AddUserDetailsResponse response = new AddUserDetailsResponse();
		response.setStatus(mapStatus(status));
		return response;
	}
		
	private Users mapUser(UserDetails userDetails) {
		Users user = new Users();
		user.setUsername(userDetails.getUsername());
		user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
		
		return user;
	}

	@PayloadRoot(namespace = "http://emiage2018s1.com/notes", localPart = "AddNoteDetailsRequest")
	@ResponsePayload
	public AddNoteDetailsResponse addNoteDetailsRequest(@RequestPayload AddNoteDetailsRequest request) {
		//vérification que le user a le droit (adduser ne doit que ajouter des utilisateurs et les les autres ne peuvent pas)
		verifieDroit(SecurityContextHolder.getContext().getAuthentication().getName(),true);
				
		Users user= userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Note noteInput = mapNote(request.getAddNoteDetails());
		
		noteInput.setUser(user);
		//System.out.println("note ajoutée debut");
		
		noteService.save(noteInput);
		
		//System.out.println("note ajoutée " + noteInput);
 		
		return mapAddNoteDetails(noteInput);
	}
	
	@PayloadRoot(namespace = "http://emiage2018s1.com/notes", localPart = "EditNoteDetailsRequest")
	@ResponsePayload
	public EditNoteDetailsResponse editNoteDetailsRequest(@RequestPayload EditNoteDetailsRequest request) {
		//vérification que le user a le droit (adduser ne doit que ajouter des utilisateurs et les les autres ne peuvent pas)
		verifieDroit(SecurityContextHolder.getContext().getAuthentication().getName(),true);

		Note noteInput = mapNote(request.getNoteDetails());
		
		Optional<Note> note = noteService.findById((long) noteInput.getIdNote());
		
		if (!note.isPresent())
			throw new NoteNotFoundException("Note non trouvée - Id " + noteInput.getIdNote());
		else {
			//vérification de l'appartenance de la note a l'utilisateur avant la suppression
			Users user= userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
			System.out.println(SecurityContextHolder.getContext().getAuthentication().getName()+" => "+user);
			System.out.println(note.get());
			//si la note n'appartient pas a l'utilisateur
			if (note.get().getUser().getIdUser()!=user.getIdUser())
				throw new ActionNotAuthorizedException("Vous n'avez pas accès à cette note - Id " + noteInput.getIdNote()	);
		
			//affectation du user
			noteInput.setUser(user);
			noteService.save(noteInput);
		}
 		
		return mapEditNoteDetails(noteInput);
	}
	
	private com.emiage2018s1.notes.Status mapStatus(Status status) {
		if (status == Status.FAILURE)
			return com.emiage2018s1.notes.Status.FAILURE;
		return com.emiage2018s1.notes.Status.SUCCESS;
	}
}
