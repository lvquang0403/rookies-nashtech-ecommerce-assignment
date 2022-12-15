import Link from "next/link";
import Layout from "../../components/layout";
import * as Yup from 'yup';
import { useForm } from "react-hook-form";
import authService from "../api/authService";
import { useRouter } from "next/router";
import { yupResolver } from '@hookform/resolvers/yup'

function SignUp() {
  const router = useRouter();
  // const [pass, setPass] = useState('');



  const handleResgister = data => {
    authService.signup(data)
    .then(res => {
      router.push("/auth/signup-success")
    })
    .catch(res => alert(res.response.data))
  };
  const userSchema = Yup.object().shape({
    firstName: Yup.string().required("First Name is required"),
    lastName: Yup.string().required("Last Name is required"),
    email: Yup.string().required("Email is required"),
    address: Yup.string().required("Address is required"),
    phone: Yup.string().required("Phone is required"),
    userName: Yup.string().required("User Name is required").min(6).max(15),
    password: Yup.string().required("Password is required").min(6).max(15),
    confirmPass: Yup.string()
    .oneOf([Yup.ref('password'), null], 'Confirm Password not match')
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
                  <span className="error text-danger">{errors.firstName?.message}</span>
                </div>
                <div className="col-md-6">
                  <label className="form-label">Last Name</label>
                  <input type="text" className="form-control"{...register("lastName")} />
                  <span className="error text-danger">{errors.lastName?.message}</span>
                </div>
                <div className="col-md-12">
                  <label className="form-label">Email</label>
                  <input type="email" className="form-control" {...register("email")} />
                  <span className="error text-danger">{errors.email?.message}</span>
                </div>
                <div className="col-md-12">
                <label className="form-label">Address</label>
                <input type="text" className="form-control" {...register("address")} />
                <span className="error text-danger">{errors.address?.message}</span>
                </div>
                <div className="col-md-12">
                <label className="form-label">Phone</label>
                <input type="number" className="form-control" {...register("phone")} />
                <span className="error text-danger">{errors.phone?.message}</span>
                </div>
                <div className="col-md-12">
                <label className="form-label">User Name</label>
                <input type="text" className="form-control" {...register("userName")}  />
                <span className="error text-danger">{errors.userName?.message}</span>
                </div>
                <div className="col-md-12">
                <label className="form-label">Password</label>
                <input type="password" className="form-control" {...register("password")} />
                <span className="error text-danger">{errors.password?.message}</span>
                </div>
                <div className="col-md-12">
                  <label className="form-label">Confirm Password</label>
                  <input type="password" className="form-control"  {...register("confirmPass")}/>
                  <span className="error text-danger">{errors.confirmPass?.message}</span>
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
