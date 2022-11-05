import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useRouter } from "next/router";
import Link from "next/link";
import { useEffect, useState } from "react";
import Layout from "../../components/layout";
import { UserProvider } from "../../context/user-context";
import { useUserContext } from "../../context/user-context";
import { yupResolver } from '@hookform/resolvers/yup'
import * as yup from 'yup'
import { useForm } from "react-hook-form";
import authService from "../api/authService";
import cartService from "../api/cartService";

function Login() {
  const router = useRouter();
  const [username, setUsername] = useState('');
  const [pass, setPass] = useState('');
  const [user, setUser] = useUserContext();

  const getNumberCartItems = (data) => {
    cartService.getNumberCartItems(data.token).then(res => {
      setUser({
        id: data.customerId,
        type: data.roles,
        name: data.lastName,
        token: data.token,
        numberCartItems: res.data
      });

      sessionStorage.setItem("user", JSON.stringify({
        id: data.customerId,
        type: data.roles,
        name: data.lastName,
        token: data.token
      }));

      router.push('/')
    }
    )
  }
  const handleLogin = () => {
    authService.signin(username, pass)
      .then(res => {
       getNumberCartItems(res.data) ;

      })
      .catch(res => alert(res.data))
  }
useEffect(()=>{
},[user])
  console.log(user)
  return (
    <div className="container py-3">
      <div className="row my-4">
        <div className="col-md-6 offset-md-3 col-lg-4 offset-lg-4">
          <div className="card border-0 shadow-sm">
            <div className="card-body px-4">
              <h4 className="card-title fw-bold mt-2 mb-4">Sign In</h4>
              <form className="row g-2">
                <div className="col-md-12">
                  <label className="form-label" >User Name</label>
                  <input
                    value={username} onChange={(e) => setUsername(e.target.value)}
                    className="form-control"
                    placeholder="Username"
                  />
                </div>
                <div className="col-md-12">
                  <label className="form-label">Password</label>
                  <input type="password" className="form-control" value={pass} onChange={(e) => setPass(e.target.value)} />
                </div>
                <div className="col-md-12">
                  {/* <Link href="/auth/forgot-password">
                    <a className="text-decoration-none">Forgot password?</a>
                  </Link> */}
                </div>
                <div className="col-md-12 mt-4">
                  <button
                    type="button"
                    className="btn btn-primary w-100"
                    onClick={() => {
                      handleLogin()
                    }}
                  >
                    Login
                  </button>
                </div>
                <div className="col-md-12">
                  <div className="row g-2">
                    <div className="col">
                      <hr className="text-muted" />
                    </div>
                    <div className="col-auto align-self-center text-muted">
                      or continue with
                    </div>
                    <div className="col">
                      <hr className="text-muted" />
                    </div>
                  </div>
                </div>

                <div className="col-md-12">
                  <div className="hstack gap-2 justify-content-center">
                    <button className="btn-facebook rounded-circle">
                      <FontAwesomeIcon icon={["fab", "facebook-f"]} />
                    </button>
                    <button className="btn-google rounded-circle">
                      <FontAwesomeIcon icon={["fab", "google"]} />
                    </button>
                    <button className="btn-apple rounded-circle">
                      <FontAwesomeIcon icon={["fab", "apple"]} />
                    </button>
                  </div>
                </div>
              </form>
            </div>
            <hr className="text-muted my-0" />
            <div className="text-center p-3">
              Don&lsquo;t hanve an account?{" "}
              <Link href="/auth/sign-up">
                <a className="text-decoration-none fw-medium">Register</a>
              </Link>
            </div>
          </div>
        </div>
      </div>
      <br />
      <br />
      <br />
    </div>
  );
}

Login.getLayout = (page) => {
  return (
    <UserProvider>
      <Layout simpleHeader hideAuth>
        {page}
      </Layout>
    </UserProvider>
  );
};

export default Login;
