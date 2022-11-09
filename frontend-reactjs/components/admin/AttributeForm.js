import React from 'react'
import * as yup from 'yup'
import { useState } from 'react';
import Link from'next/link'
import { useUserContext } from '../../context/user-context';
import { useForm } from "react-hook-form";
import { yupResolver } from '@hookform/resolvers/yup'
import attributeService from '../../pages/api/attributeService';
const AttributeForm = ( {update,  attribute, setAttributes, attributes, setStatus} ) => {
  const [user, setUser] = useUserContext()
  const userSchema = yup.object().shape({
    attributeName: yup.string().required("Attribute Name is required"),
  })

  const onSubmit = (data) => {
    // const data = getValues()
    if(update) {
     attributeService.updateAttribute(user.token, attribute.attributeId, data.attributeName)
     .then(res => {
      const filter = attributes.filter(c => c.attributeId !== attribute.attributeId)
      setAttributes([...filter, res.data])
      setStatus({update: false, show: false})
     })
    }
    else{
      attributeService.createAttribute(user.token, data.attributeName)
      .then(res => {
        console.log(res)
        setAttributes([...attributes, res.data])
        setStatus({update: false, show: false})
      })
      .catch(res => alert(res.response.data.message))

    }
  }

  const { register,setValue, handleSubmit, watch, getValues,  formState: { errors } } = useForm({
    resolver: yupResolver(userSchema)
  });
  setValue("attributeName", attribute.attributeName)
  return (
    <div className="card border-0 shadow-sm w-50">
      <div className="card-body px-4">
        <h4 className="card-title fw-bold mt-2 mb-4">Attribute Form</h4>
        <form className="row g-2" onSubmit={handleSubmit(onSubmit)}>
          <div className="col-md-12">
            <label className="form-label" >Attribute Name</label>
            <input
              {...register("attributeName")} 
              className="form-control"
              placeholder="Attribute Name"
            />
            <span className="error text-danger">{errors.attributeName?.message}</span>
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

export default AttributeForm
