package com.example.library.helper;

import com.example.library.entity.Writer;
import com.example.library.repository.WriterRepository;
import com.example.library.request.WriterRequest;
import com.example.library.response.Response;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Data
@Component
@RequiredArgsConstructor
public class WriterHelper {

    @Autowired
    Response response;

    @Autowired
    private WriterRepository writerRepository;

    public Writer requestToEntity(WriterRequest request) {
        Writer writer = null;
        if (Objects.nonNull(request)) {
            writer = new Writer();
            BeanUtils.copyProperties(request, writer);
        }
        return writer;
    }

    public Response save(Writer writer){
        if (Objects.nonNull(writer)) {
            writerRepository.save(writer);
            response.setSuccess(true);
            response.setMessage("Writer Added");
            response.setData(writer);
        } else {
            response.setSuccess(false);
            response.setMessage("Writer can not be added");
        }
        return response;
    }
}
