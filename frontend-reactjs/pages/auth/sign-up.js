import Link from "next/link";
import Layout from "../../components/layout";
import * as yup from 'yup'
import { useForm } from "react-hook-form";
import authService from "../api/authService";
import { useRouter } from "next/router";
import { yupResolver } from '@hookform/resolvers/yup'

function SignUp() {
  const router = useRouter();
  // const [pass, setPass] = useState('');



  const handleResgister = data => {
    console.log(data)
  };
  const userSchema = yup.object().shape({
    userName: yup.string().required("User Name is required").min(6).max(12),
    password: yup.string().required("Password is required").min(6).max(15)
  })
  const { register, handleSubmit, watch, formState: { errors } } = useForm({
    resolver: yupResolver(userSchema)
});
  return (
    <div className="container py-3">
      <div className="row my-4">
        <div className="col-md-8 offset-md-2 col-lg-6 offset-lg-3">
          <div className="card border-0 shadow-sm">
            <div className="card-body px-4">
              <h4 className="card-title fw-bold mt-2 mb-4">Sign Up</h4>
              <form className="row g-3" onSubmit={handleSubmit(handleResgister)}>
                <div className="col-md-6">
                  <label className="form-label">First Name</label>
                  <input type="text" className="form-control" {...register("firstName")}/>
                  <span className="error text-danger">{errors.userName?.message}</span>
                </div>
                <div className="col-md-6">
                  <label className="form-label">Last Name</label>
                  <input type="text" className="form-control"{...register("lastName")} />
                </div>
                <div className="col-md-12">
                  <label className="form-label">Email</label>
                  <input type="email" className="form-control" {...register("email")} />
                </div>
                <div className="col-md-12">
                <label className="form-label">Address</label>
                <input type="text" className="form-control" {...register("address")} />
                </div>
                <div className="col-md-12">
                <label className="form-label">Phone</label>
                <input type="text" className="form-control" {...register("phone")} />
                </div>
                <div className="col-md-12">
                <label className="form-label">User Name</label>
                <input type="email" className="form-control" {...register("userName")}  />
                </div>
                <div className="col-md-12">
                <label className="form-label">Password</label>
                <input type="password" className="form-control" {...register("password")} />
                </div>
                <div className="col-md-12">
                  <label className="form-label">Confirm Password</label>
                  <input type="password" className="form-control"  {...register("confirmPass")}/>
                </div>
                <div className="col-md-12 mt-4">
                  <input className="btn btn-primary w-100" type="submit" value="Register"/>
                </div>
                <div className="col-md-12">
                  <div className="text-muted bg-light rounded p-3 border small">
                    By clicking the &lsquo;Sign Up&lsquo; button, you confirm
                    that you accept our{" "}
                    <a href="#">Terms of use and Privacy Policy</a>.
                  </div>
                </div>
              </form>
              <hr className="text-muted" />
              <div className="text-center">
                Already have an account?{" "}
                <Link href="/auth/login">
                  <a className="text-decoration-none fw-medium">Login</a>
                </Link>
              </div>
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

SignUp.getLayout = (page) => {
  return (
    <Layout simpleHeader hideAuth>
      {page}
    </Layout>
  );
};

export default SignUp;
