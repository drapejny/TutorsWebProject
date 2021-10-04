package by.slizh.tutorsweb.model.dao;

import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.model.entity.Tutor;

import java.util.List;
import java.util.Optional;

public abstract class TutorDao extends AbstractDao<Tutor> {

    public abstract Optional<Tutor> findTutorByEmail(String email) throws DaoException;

    public abstract List<Tutor> searchTutors(int subjectId, String city, int minPrice, int maxPrice, int offset, int numberOfRecords, String sort) throws DaoException;

    public abstract int countSearchedRecords(int subjectId, String city, int minPrice, int maxPrice) throws DaoException;

    public abstract List<String> findAllCities() throws DaoException;

}
