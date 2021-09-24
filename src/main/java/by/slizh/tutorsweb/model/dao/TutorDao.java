package by.slizh.tutorsweb.model.dao;

import by.slizh.tutorsweb.exception.DaoException;
import by.slizh.tutorsweb.model.entity.Tutor;

import java.util.Optional;

public abstract class TutorDao extends AbstractDao<Integer, Tutor> {

    public abstract Optional<Tutor> findTutorByEmail(String email) throws DaoException;


}
