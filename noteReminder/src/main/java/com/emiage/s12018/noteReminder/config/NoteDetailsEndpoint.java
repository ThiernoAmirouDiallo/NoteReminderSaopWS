package com.emiage.s12018.noteReminder.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.emiage.s12018.noteReminder.entity.Note;
import com.emiage.s12018.noteReminder.exception.NoteNotFoundException;
import com.emiage.s12018.noteReminder.service.NoteRepository;
import com.emiage2018s1.notes.AddNoteDetails;
import com.emiage2018s1.notes.AddNoteDetailsRequest;
import com.emiage2018s1.notes.AddNoteDetailsResponse;
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

@Endpoint
public class NoteDetailsEndpoint {

	@Autowired
	NoteRepository service;

	// method
	// input - GetNoteDetailsRequest
	// output - GetNoteDetailsResponse

	// http://emiage2018s1.com/notes
	// GetNoteDetailsRequest
	@PayloadRoot(namespace = "http://emiage2018s1.com/notes", localPart = "GetNoteDetailsRequest")
	@ResponsePayload
	public GetNoteDetailsResponse processNoteDetailsRequest(@RequestPayload GetNoteDetailsRequest request) {

		Optional<Note> note = service.findById((long) request.getId());

		if (!note.isPresent())
			throw new NoteNotFoundException("Invalid Note Id " + request.getId());

		return mapNoteDetails(note.get());
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

		noteDetails.setIdSondage(note.getIdSondage().intValue());

		noteDetails.setTexte(note.getTexte());
		noteDetails.setCouleur(note.getCouleur());
		noteDetails.setEcheance(note.getEcheance());
		

		noteDetails.setOrdre((int) note.getOrdre());
		return noteDetails;
	}
	
	private Note mapNote(NoteDetails noteDetails) {
		Note note = new Note();

		note.setIdSondage((long) noteDetails.getIdSondage());

		note.setTexte(noteDetails.getTexte());
		note.setCouleur(noteDetails.getCouleur());
		note.setEcheance(noteDetails.getEcheance());
		

		note.setOrdre((int) noteDetails.getOrdre());
		return note;
	}
	
	private Note mapNote(AddNoteDetails noteDetails) {
		Note note = new Note();

		note.setIdSondage(null);

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

		List<Note> notes = service.findAll();

		return mapAllNoteDetails(notes);
	}

	@PayloadRoot(namespace = "http://emiage2018s1.com/notes", localPart = "DeleteNoteDetailsRequest")
	@ResponsePayload
	public DeleteNoteDetailsResponse deleteNoteDetailsRequest(@RequestPayload DeleteNoteDetailsRequest request) {

		Status status = Status.FAILURE;
		Optional<Note> note = service.findById((long) request.getId());
		
		if(note.isPresent()) {
			service.deleteById((long) request.getId());
			status = Status.SUCCESS;
		}
 		
		DeleteNoteDetailsResponse response = new DeleteNoteDetailsResponse();
		response.setStatus(mapStatus(status));
		return response;
	}
		
	@PayloadRoot(namespace = "http://emiage2018s1.com/notes", localPart = "AddNoteDetailsRequest")
	@ResponsePayload
	public AddNoteDetailsResponse addNoteDetailsRequest(@RequestPayload AddNoteDetailsRequest request) {

		Note noteInput = mapNote(request.getAddNoteDetails());
		System.out.println("note ajoutée debut");
		
		service.save(noteInput);
		
		System.out.println("note ajoutée " + noteInput);
 		
		return mapAddNoteDetails(noteInput);
	}
	
	@PayloadRoot(namespace = "http://emiage2018s1.com/notes", localPart = "EditNoteDetailsRequest")
	@ResponsePayload
	public EditNoteDetailsResponse editNoteDetailsRequest(@RequestPayload EditNoteDetailsRequest request) {

		Note noteInput = mapNote(request.getNoteDetails());
		
		Optional<Note> note = service.findById((long) noteInput.getIdSondage());
		
		if(note.isPresent()) {
			service.save(noteInput);
		}
 		
		return mapEditNoteDetails(noteInput);
	}
	
	private com.emiage2018s1.notes.Status mapStatus(Status status) {
		if (status == Status.FAILURE)
			return com.emiage2018s1.notes.Status.FAILURE;
		return com.emiage2018s1.notes.Status.SUCCESS;
	}
}
