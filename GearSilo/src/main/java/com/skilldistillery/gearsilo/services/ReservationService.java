package com.skilldistillery.gearsilo.services;

import java.security.Principal;
import java.util.List;

import com.skilldistillery.gearsilo.entities.Reservation;

public interface ReservationService {

	public List<Reservation> findAll(String username);

	public Reservation findReservationById(String username, int id);
	
	public Reservation createReservation(String username, Reservation reservation);
	
	public Reservation updateReservation(String username, Reservation reservation, int id);
	
	public boolean deleteReservation(String username, int id);

}
