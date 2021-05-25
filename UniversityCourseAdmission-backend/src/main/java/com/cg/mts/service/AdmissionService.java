package com.cg.mts.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mts.entities.Admission;
import com.cg.mts.entities.Course;
import com.cg.mts.exceptions.DuplicateDataException;
import com.cg.mts.repository.IAdmissionRepository;

@Service
public class AdmissionService implements IAdmissionService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	IAdmissionRepository repository;

	// Method to add admission
	public void addAdmission(Admission a, int cid, int sid, int aid) throws DuplicateDataException {
		if (repository.existsById(a.getAdmissionId()))
			throw new DuplicateDataException("Admission with" + a.getAdmissionId() + "already exists...");
		repository.saveByApplicantAndAdmissionCommiteeId(a.getAdmissionId(), a.getAdmissionDate(), cid,
				a.getStatus().toString(), sid, aid);
	}
	
	public void addingAdmission(Admission a) throws DuplicateDataException {
		if (repository.existsById(a.getCourseId()))
			throw new DuplicateDataException("Admission with" + a.getAdmissionId() + "Already exists");
		repository.save(a);
	}

	
	// Method to update admission
	public boolean updateAdmission(Admission a, int aid) {
		if (repository.existsById(aid)) {
			repository.updateAdmissionTable(a.getAdmissionDate(), a.getStatus(), aid);
			return true;
		}
		return false;

	}

	// Method to delete admission
	public boolean deleteAdmission(int id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	// Method to find admission by admission id
	public Admission getAdmission(int id) {

		Optional<Admission> opt = repository.findById(id);
		if (opt.isPresent())
			return opt.get();
		return null;
	}

	// Method to view all admission by date
	public List<Admission> showAllByDate(String adDate) {
		return em.createQuery("Select a from Admission a where a.admissionDate = '" + adDate + "'", Admission.class)
				.getResultList();
	}

	// Method to view all admission by course id
	public List<Admission> showAllByCourseId(int id) {
		return em.createQuery("Select a from Admission a where a.courseId = '" + id + "'", Admission.class)
				.getResultList();
	}
	
	@Override
	public List<Admission> getallAdmission() {
		List<Admission> list = (List<Admission>) repository.findAll();
		return list;
	}

}
