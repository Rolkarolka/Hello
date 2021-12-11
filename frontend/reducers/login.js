import { SIGN_IN, REGISTER, CHECK_LOGIN } from '../actions/login';

const initialState = {
  token: null,
};

export default (state = initialState, action) => {
  switch (action.type) {
    case SIGN_IN:
      return {
        ...state,
        token: action.token,
      }; 
    // case REGISTER:
    //   return {
    //       ...state,
    //       registerOutcome: action.outcome,
    //   };
    // case CHECK_LOGIN:
    //   return {
    //       ...state,
    //       isLoginAvilable: action.outcome,
    //   };
  }
  return state;
};
