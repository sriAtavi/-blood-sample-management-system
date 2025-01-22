package com.Atavi.bsm.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStructure <T>
{
    private int status;
    private String message;
    private T data;


    public static <T> ResponseStructure<T> createResponse(int status,String message,T data)
    {
        ResponseStructure<T> structure = new ResponseStructure<T>();
            structure.setStatus(status);
            structure.setMessage(message);
            structure.setData(data);

        return structure;
    }
}
