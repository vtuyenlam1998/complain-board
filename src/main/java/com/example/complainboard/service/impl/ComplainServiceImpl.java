package com.example.complainboard.service.impl;


import com.example.complainboard.conveter.ComplainConverter;
import com.example.complainboard.mapper.ComplainMapper;
import com.example.complainboard.mapper.UserMapper;
import com.example.complainboard.model.Complain;
import com.example.complainboard.model.User;
import com.example.complainboard.pageable.MyBatisPageable;
import com.example.complainboard.payload.request.CreateComplainRequestDTO;
import com.example.complainboard.payload.request.EditComplainRequestDTO;
import com.example.complainboard.payload.response.ComplainResponseDTO;
import com.example.complainboard.payload.response.CurrentUserResponseDTO;
import com.example.complainboard.payload.response.PageResponseDTO;
import com.example.complainboard.service.ComplainService;
import com.example.complainboard.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplainServiceImpl implements ComplainService{
    private final ComplainConverter complainConverter;
    private final ComplainMapper complainMapper;
    private final UserMapper userMapper;
    private final UserService userService;

    @Autowired
    public ComplainServiceImpl(ComplainMapper complainMapper, ComplainConverter complainConverter, UserMapper userMapper, UserService userService) {
        this.complainConverter = complainConverter;
        this.complainMapper = complainMapper;
        this.userMapper = userMapper;
        this.userService = userService;
    }
    @Override
    public List<Complain> findAll() {
        return complainMapper.getAllComplains();
    }

    @Override
    public ComplainResponseDTO findById(Long id) {
        // Fetch the "Complain" entity with the given ID from the database.
        Complain complain = complainMapper.getComplainById(id);

        // Convert the retrieved "Complain" entity into a response DTO.
        return complainConverter.convertEntityToResponseDTO(complain);
    }

    @Override
    public Complain save(CreateComplainRequestDTO createComplainRequestDTO) throws IllegalAccessException {
        // Get the currently logged-in user's information.
        CurrentUserResponseDTO user = userService.getCurrentUser();

        // Find the User entity associated with the logged-in user's username.
        User currentUser = userMapper.findByUsername(user.getUsername());

        // Convert the CreateComplainRequestDTO into a Complain entity.
        Complain complain = complainConverter.convertCreateRequestDTOToEntity(createComplainRequestDTO);

        // Set the User entity as the owner of the Complain.
        complain.setUser(currentUser);

        // Insert (save) the Complain entity into the database using complainMapper.
        complainMapper.insertComplain(complain);

        // Return the saved Complain entity.
        return complain;
    }

    @Override
    public Complain update(EditComplainRequestDTO editComplainRequestDTO) {
        // Convert the EditComplainRequestDTO into a Complain entity.
        Complain complain = complainConverter.convertEditRequestDTOToEntity(editComplainRequestDTO);

        // Update (save) the Complain entity in the database using complainMapper.
        complainMapper.updateComplain(complain);

        // Return the updated Complain entity.
        return complain;
    }

    @Override
    public Complain delete(Long id) {
        // Fetch the Complain entity with the given ID from the database.
        Complain complain = complainMapper.getComplainById(id);

        // Delete the Complain entity from the database using complainMapper.
        complainMapper.deleteComplain(id);

        // Return the deleted Complain entity.
        return complain;
    }

    @Override
    public PageResponseDTO findByPage(MyBatisPageable pageable) {
        // Calculate the offset for the SQL query based on the requested page and page size.
        int offset = pageable.getPage() * pageable.getSize();

        // Create a RowBounds object to limit the query results to the specified range.
        RowBounds rowBounds = new RowBounds(offset,pageable.getSize());

        // Fetch a list of "Complain" entities using MyBatis with pagination.
        List<Complain> complainList = complainMapper.getComplainsByPage(rowBounds);

        // Get the total record count (total number of "Complain" entities in the database).
        long totalRecordCount = complainMapper.getTotalRecordCount();

        // Calculate the total number of pages based on the total record count and page size.
        long totalPage = (totalRecordCount + pageable.getSize() - 1) / pageable.getSize();

        // Create a new PageResponseDTO to hold the results.
        PageResponseDTO responseDTO = new PageResponseDTO();

        // Convert the list of "Complain" entities to a list of DTOs.
        responseDTO.setComplainList(complainConverter.convertListEntityToListDTO(complainList));

        // Set the page number in the response DTO.
        responseDTO.setPageNumber(pageable.getPage());

        // Set the total number of pages in the response DTO.
        responseDTO.setTotalPages(totalPage);

        // Determine if there is a next page.
        responseDTO.setHasNext(pageable.getPage() < totalPage - 1);

        // Determine if there is a previous page.
        responseDTO.setHasPrevious(pageable.getPage() > 0);

        // Return the populated PageResponseDTO.
        return responseDTO;
    }
}
