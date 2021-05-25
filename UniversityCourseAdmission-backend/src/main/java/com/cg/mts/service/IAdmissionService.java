package com.cg.mts.service;

import java.util.List;

import com.cg.mts.entities.Admission;

public interface IAdmissionService {

	void addAdmission(Admission a, int cid, int sid, int aid);

	boolean deleteAdmission(int id);

	Admission getAdmission(int id);
	
	List<Admission> getallAdmission();

	List<Admission> showAllByDate(String adDate);

	List<Admission> showAllByCourseId(int id);

	boolean updateAdmission(Admission a, int aid);

}
