package com.example.library.serviceImpl;

import com.example.library.entity.Book;
import com.example.library.entity.Writer;
import com.example.library.enums.RecordStatus;
import com.example.library.helper.WriterHelper;
import com.example.library.repository.WriterRepository;
import com.example.library.request.WriterRequest;
import com.example.library.response.Response;
import com.example.library.service.WriterService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WriterServiceImpl implements WriterService {


    @Autowired
    private WriterRepository writerRepository;
    @Autowired
    private WriterHelper writerHelper;
    @Autowired
    private EntityManager entityManager;

    @Override
    public Response save(WriterRequest writerRequest) {
        Writer writer = writerHelper.requestToEntity(writerRequest);
        return writerHelper.save(writer);
    }

    @Override
    public Response update(Writer writer) {

        Response response = findById(writer.getId());

        if (response.isSuccess() == true) {
            writerRepository.save(writer);
            response.setSuccess(true);
            response.setMessage("Writer Updated");
            response.setData(writer);
        }

        return response;
    }

    @Override
    public Response delete(Long id) {
        Response response = new Response();

        Optional<Writer> writer = writerRepository.findById(id);
        if (writer.isPresent()) {
            Writer b = writer.get();
            b.setRecordStatus(RecordStatus.DELETED);
            writerRepository.save(b);
            response.setSuccess(true);
            response.setMessage("Book Deleted");
        } else {
            response.setSuccess(false);
            response.setMessage("Writer Not Found");
        }
        return response;
    }

    @Override
    public Response findById(Long id) {
        Response response = new Response();
        Optional<Writer> writer = writerRepository.findById(id);
        if (writer.isPresent()) {
            response.setSuccess(true);
            response.setData(writer.get());
        } else {
            response.setSuccess(false);
            response.setMessage("Resource not found with id : " + id);
        }
        return response;
    }

    @Override
    public Response getAll(String search, String sortBy) {
        Response response = new Response();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Writer> criteriaQuery = criteriaBuilder.createQuery(Writer.class);
        Root<Writer> root = criteriaQuery.from(Writer.class);

        // Apply Search Predicate
        Predicate searchPredicate = criteriaBuilder.like(root.get("writerName"), "%" + search + "%");

        Order sortOrder = sortBy.equals("asc") ? criteriaBuilder.asc(root.get("id")) : criteriaBuilder.desc(root.get("id"));

        criteriaQuery.where(searchPredicate);
        criteriaQuery.orderBy(sortOrder);

        TypedQuery<Writer> typedQuery = entityManager.createQuery(criteriaQuery);
        response.setData(typedQuery.getResultList());
        response.setMessage("All Writer List");
        response.setSuccess(true);
        return response;
    }

    @Override
    public Response getList(Integer size, Integer page, String sortBy, String search) {
        Response response = new Response();

        // Create pageable object for pagination and sorting
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        // Perform search and retrieve paginated result
        Page<Writer> result;

        if (search != null && !search.isEmpty()) {
            result = writerRepository.findByWriterNameContainingIgnoreCase(search, pageable);
        } else {
            result = writerRepository.findAll(pageable);
        }

        response.setData(result);
        response.setMessage("Data retrieved successfully");
        response.setSuccess(true);
        return response;
    }
}
