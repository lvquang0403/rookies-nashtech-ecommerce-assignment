import React from 'react'
import { useState } from 'react';
import Link from'next/link'
import * as yup from 'yup'
import { useUserContext } from '../../context/user-context';
import { useForm } from "react-hook-form";
import { yupResolver } from '@hookform/resolvers/yup'
import categoryService from '../../pages/api/categoryService';
const CategoryForm = ( {update,  category, setCategories, categories, setStatus} ) => {
  const [user, setUser] = useUserContext()
  const userSchema = yup.object().shape({
    categoryName: yup.string().required("CategoryName is required"),
  })

  const onSubmit = (data) => {
    console.log("submit")
    if(update) {
     categoryService.updateCategory(user.token, category.categoryId, data.categoryName)
     .then(res => {
      const filter = categories.filter(c => c.categoryId !== category.categoryId)
      setCategories([...filter, res.data])
      setStatus({update: false, show: false})
      // setCategories([...filter, category])
     })
    }
    else{
      categoryService.createCategory(user.token, data.categoryName)
      .then(res => {
        setCategories([...categories, res.data])
        setStatus({update: false, show: false})
      })
      .catch(res => alert(res.response.data.message))

    }
  }

  const { register,setValue, handleSubmit, formState: { errors } } = useForm({
    resolver: yupResolver(userSchema)
  });
  setValue("categoryName", category.categoryName)
  return (
    <div className="card border-0 shadow-sm w-50">
      <div className="card-body px-4">
        <h4 className="card-title fw-bold mt-2 mb-4">Category Form</h4>
        <form className="row g-2" onSubmit={handleSubmit(onSubmit)}>
          <div className="col-md-12">
            <label className="form-label" >Category Name</label>
            <input
              {...register("categoryName")} 
              className="form-control"
              placeholder="Category Name"
            />
            <span className="error text-danger">{errors.categoryName?.message}</span>
          </div>
          <div className="col-md-12">
            {/* <Link href="/auth/forgot-password">
            <a className="text-decoration-none">Forgot password?</a>
          </Link> */}
          </div>
          <div className="col-md-12 mt-4">
            <input
              type="submit"
              className="btn btn-primary w-100"
              // onClick={() => {
              //   handleLogin()
              // }}
              value={update ? "Update" : "Create"}
            />
            {/* Login */}

          </div>
          <div className="col-md-12">
            <div className="row g-2">
              <div className="col">
                <hr className="text-muted" />
              </div>
              <div className="col">
                <hr className="text-muted" />
              </div>
            </div>
          </div>
        </form>
      </div>
      <hr className="text-muted my-0" />
    </div>
  )
}

export default CategoryForm
