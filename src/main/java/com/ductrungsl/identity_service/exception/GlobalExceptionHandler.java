package com.ductrungsl.identity_service.exception;

import com.ductrungsl.identity_service.dto.request.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

// Annotation khai báo nơi lưu trữ tất cả exception / error
@ControllerAdvice
public class GlobalExceptionHandler {
    // Handle RuntimeException
    @ExceptionHandler(value={RuntimeException.class})
//    ResponseEntity<String> handlingRuntimeException(RuntimeException exception){
//        // Trả về message đã ghi trong service
//        return ResponseEntity.badRequest().body(exception.getMessage());
//    }
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception){
        // Theo cấu trúc class ApiResponse
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(1001);
        apiResponse.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value={Exception.class})
    ResponseEntity<ApiResponse> handlingAppException(RuntimeException exception){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(ErrorCode.UNCATEGORIZED.getCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value={AppException.class})
    ResponseEntity<ApiResponse> handlingAppException(AppException exception){
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    // Handle ValidateException
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    ResponseEntity <String> handlingValidation(MethodArgumentNotValidException exception){
//        // getDefaultMessage(): string parameter (service)
//        return ResponseEntity.badRequest().body(exception.getFieldError().getDefaultMessage());
//    }
        // Dùng enum ErorrCode
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity <ApiResponse> handlingValidation(MethodArgumentNotValidException exception){
        String enumKey = exception.getFieldError().getDefaultMessage();

        // mặc định là sai key trong enum
        ErrorCode errorCode = ErrorCode.INVALID_KEY;

        try{
            // nếu tìm thấy key thì gán lại
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException e){

        }


        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }
}
