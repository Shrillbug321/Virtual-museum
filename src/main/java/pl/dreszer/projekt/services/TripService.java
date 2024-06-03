package pl.dreszer.projekt.services;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import pl.dreszer.projekt.exceptions.PaintingNotFoundException;
import pl.dreszer.projekt.models.Guider;
import pl.dreszer.projekt.models.Painting;
import pl.dreszer.projekt.models.Trip;
import pl.dreszer.projekt.models.authorization.User;
import pl.dreszer.projekt.repositories.GuidersRepository;
import pl.dreszer.projekt.repositories.TripRepository;
import pl.dreszer.projekt.repositories.UsersRepository;

import java.io.IOException;
import java.net.Authenticator;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class TripService {

	@Autowired
	TripRepository tripRepository;
	@Autowired
	GuidersRepository guidersRepository;
	@Autowired
	UsersRepository usersRepository;

	public void showForm(int tripId, Model model, boolean edit)
	{
		if (edit && tripId > 0)
		{
			model.addAttribute("trip", tripRepository.getById(tripId));
		}
		else
		{
			model.addAttribute("trip", new Trip());
		}
		model.addAttribute("edit", edit);
	}

	public String processForm(Model model, Trip trip, BindingResult result, boolean edit)
	{
		if (result.hasErrors())
			return "form";
		tripRepository.save(trip);
		model.addAttribute("trip", trip);
		model.addAttribute("edit", edit);
		return "success";
	}

	public void tripDetails(Model model, int tripId)
	{
		Trip trip = tripRepository.getById(tripId);
		if (!tripRepository.existsById(tripId))
		{
			throw new PaintingNotFoundException();
		}
		model.addAttribute("trip", trip);
	}

	public void trips(Model model)
	{
		List<Trip> trips = tripRepository.findAll();
		Map<Integer, Integer> saved = new HashMap<>();
		Map<Integer, Integer> daysToStart = new HashMap<>();
		User user = getCurrentUser();
		for (Trip trip : trips)
		{
			int tripId = trip.getTripId();
			for (User u : trip.getUsers())
			{
				if (u.getUser_id() == user.getUser_id())
				{
					saved.put(tripId, 1);
					break;
				}
			}
			saved.putIfAbsent(tripId, 0);

			int days = (int) ChronoUnit.DAYS.between(LocalDate.now(), trip.getDate());
			daysToStart.put(tripId, days);
		}
		model.addAttribute("trips", trips);
		model.addAttribute("saved", saved);
		model.addAttribute("daysToStart", daysToStart);
	}

	public void save(int tripId)
	{
		Trip trip = tripRepository.getById(tripId);
		User user = getCurrentUser();
		Set<User> a = trip.getUsers();
		a.add(user);
		trip.setUsers(a);
		tripRepository.save(trip);
	}

	public void unsave(int tripId)
	{
		Trip trip = tripRepository.getById(tripId);
		User user = getCurrentUser();
		Set<User> a = trip.getUsers();
		a.remove(user);
		trip.setUsers(a);
		tripRepository.save(trip);
	}

	public void delete(Model model, int tripId)
	{
		Trip trip = tripRepository.getById(tripId);
		model.addAttribute("subject", trip.getSubject());
		tripRepository.delete(trip);
	}
	public List<Guider> loadGuiders()
	{
		return guidersRepository.findAll();
	}

	private User getCurrentUser()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userD = (UserDetails)authentication.getPrincipal();
		return usersRepository.findByUsername(userD.getUsername());
	}
}
