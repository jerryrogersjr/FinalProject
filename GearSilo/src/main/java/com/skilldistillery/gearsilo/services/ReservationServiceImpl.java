package com.skilldistillery.gearsilo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gearsilo.entities.Reservation;
import com.skilldistillery.gearsilo.repositories.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository resRepo;

	@Override
	public List<Reservation> findAll() {
		return resRepo.findAll();
	}

	@Override
	public Reservation findReservationById(int id) {
		return null;
	}

	@Override
	public Reservation createReseration(Reservation reservation) {
		return null;
	}

	@Override
	public Reservation updateReservation(Reservation reservation, int id) {
		return null;
	}

	@Override
	public boolean deleteReservation(int id) {
		return false;
	}
}
