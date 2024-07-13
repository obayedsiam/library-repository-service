package com.example.library.helper;

import com.example.library.entity.User;
import com.example.library.repository.UserRepository;
import com.example.library.request.UserRequest;
import com.example.library.response.Response;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
@RequiredArgsConstructor
public class UserHelper {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private WriterRepository writerRepository;

    @Autowired
    private Response response;

    public Response save(UserRequest userRequest) {
        User user = new User();
        BeanUtils.copyProperties(userRequest, user);

//        List<Writer> writerList = new ArrayList<>();
//
//        for(Long writerId : bookRequest.getWriterIds()){
//            Optional<Writer> writer = writerRepository.findById(writerId);
//            if(writer.isPresent()) writerList.add(writer.get());
//        }
//
//        book.setWriterList(writerList);
//        book.setRecordStatus(RecordStatus.ACTIVE);
//        bookRepository.save(book);
//        response.setData(book);
//        response.setSuccess(true);
//        response.setMessage("Saved Successfully");

        return response;
    }

    public Response update(UserRequest request, User user) {
        BeanUtils.copyProperties(request, user);
//        List<Writer> writerList = new ArrayList<>();

//        for (Long writerId : request.getWriterIds()) {
//            Optional<Writer> writer = writerRepository.findById(writerId);
//            if (writer.isPresent()) writerList.add(writer.get());
//        }
//        book.setWriterList(writerList);
//        bookRepository.save(book);
//        response.setSuccess(true);
//        response.setMessage("Book Updated");
//        response.setData(book);
        return response;
    }

    public Response delete(User user) {
        User u = user;
//        b.setRecordStatus(RecordStatus.DELETED);
//        bookRepository.save(b);
//        response.setSuccess(true);
//        response.setMessage("Book Deleted");
        return response;
    }

}
