package com.example.graphql.ui.exceptions;

import org.springframework.graphql.execution.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

@Component
public class ErrorHandlingGraphqlJava extends DataFetcherExceptionResolverAdapter {

    public GraphQLError resolveToSingleError(Throwable e, DataFetchingEnvironment env){
        return GraphqlErrorBuilder.newError()
                .errorType(ErrorType.NOT_FOUND)
                .message(e.getMessage())
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .build();
    }


}
