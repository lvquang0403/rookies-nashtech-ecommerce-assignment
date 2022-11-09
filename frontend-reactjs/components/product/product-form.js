import { useState } from "react";
import { Formik, Field, Form } from "formik";
import fileService from "../../pages/api/fileService";
import { useUserContext } from "../../context/user-context";
import { useEffect } from "react";
import productService from "../../pages/api/productService";
import { set } from "react-hook-form";
import { route } from "next/dist/server/router";
import { useRouter } from 'next/router'


function ProductForm({ initialProduct, attributeList, categoryList, update, setProduct, productId }) {
    const [user, setUser] = useUserContext();
    const [selectedColor, setSelectedColor] = useState('');
    const [currentColor, setCurrentColor] = useState('');
    const [colorList, setColorList] = useState([]);
    const [selectedAttr, setSelectedAttr] = useState('');
    const [selectedAttrList, setSelectedAttrList] = useState([]);
    const router = useRouter()

    useEffect(() => {
        if (update) {
            setColorList(initialProduct.images)
        }
    }, [update, initialProduct])
    // const attributeList = [
    //     {
    //         attributeId: 1,
    //         attributeName: 'CPU'
    //     },
    //     {
    //         attributeId: 2,
    //         attributeName: 'RAM'
    //     },
    //     {
    //         attributeId: 3,
    //         attributeName: 'SSD'
    //     }
    // ]

    // const categoryList = [
    //     {
    //         categoryId: 1,
    //         categoryName: 'Laptop'
    //     },
    //     {
    //         categoryId: 2,
    //         categoryName: 'Phone'
    //     },
    //     {
    //         categoryId: 3,
    //         categoryName: 'Tablet'
    //     }
    // ]


    // const attributes = {};
    // attributeList.forEach(a => {
    //     attributes[a.attributeId.toString()] = '';
    // })

    // initialProduct.attributesMap = attributes;


    const handleRemoveAtt = (id) => {
        const newAttributes = initialProduct.attributes.filter(at => at.attributeId !== id)
        setProduct({ ...initialProduct, attributes: newAttributes })
    }

    const handleRemoveColor = (color) => {
        // const newImages = initialProduct.images.filter(img => img.color !== color)
        // setProduct({ ...initialProduct, images: newImages })
        setColorList(colorList.filter(c => c.color !== color))

    }

    const onSelectedAttrChanged = (e) => {
        setSelectedAttr(e.target.value);
    }

    const onAddAttr = () => {
        if (selectedAttr && !initialProduct.attributes.find(a => a.attributeId.toString() === selectedAttr)) {

            const newAttributes = [...initialProduct.attributes, attributeList.find(a => a.attributeId.toString() === selectedAttr)]
            setProduct({ ...initialProduct, attributes: newAttributes })
            // setSelectedAttrList([...selectedAttrList, attributeList.find(a => a.attributeId.toString() === selectedAttr)])
        }
    }

    const onAddColor = () => {
        if (currentColor && !colorList.find(c => c.color === currentColor)) {
            setColorList([...colorList, { color: currentColor, url: '' }])
        }
    }

    const onFileSelected = (e, color) => {
        // e.target.files[0]
        fileService.uploadImage(user.token, e.target.files[0])
            .then(res => {
                const newImageColor = colorList.find(e => e.color === color);
                newImageColor.url = res.data;
                setColorList([...colorList.filter(e => e.color !== color), newImageColor]);
            })
    }

    const onSubmit = (values) => {
        // const submittedAttributes = [];
        // for (const attr of selectedAttrList) {
        //     submittedAttributes.push({
        //         attributeId: attr.attributeId,
        //         value: values.attributesMap[attr.attributeId]
        //     })
        // }
        // values.attributes = 
        // values.images = colorList;
        delete values.attributesMap
        if (update) {
            initialProduct.images = colorList
            initialProduct.categoryId = parseInt(initialProduct.categoryId)
            initialProduct.price = parseInt(initialProduct.price)
            productService.updateProduct(user.token, initialProduct, productId)
                .then(res => {
                    alert("Update Successfully")
                    router.push("/admin/products")})
                .catch(res => console.log(res))
        }
        else {
            initialProduct.images = colorList
            initialProduct.categoryId = parseInt(initialProduct.categoryId)
            initialProduct.price = parseInt(initialProduct.price)
            console.log(initialProduct)
            productService.addProduct(user.token, initialProduct)
                .then(res => {
                    alert("Create Successfully")
                    router.push("/admin/products")
                })
                .catch(res => console.log(res))
        }

        // productService.addProduct(user.token, values)
        // .then(res => console.log(res))
    }
    console.log(initialProduct)
    return (
        <div className="vstack">
            <Formik initialValues={initialProduct} enableReinitialize={true} onSubmit={onSubmit}>
                <Form>
                    <div className="bg-white mb-4">
                        <div className="container py-4">
                            <div className="row gy-3 gx-4">
                                <div className="col-lg-5">
                                    <div className="row">
                                        <div className="col-12">
                                            <div className="ratio ratio-1x1">
                                                {selectedColor && (<img
                                                    className="rounded"
                                                    src={colorList.find(e => e.color === selectedColor)?.url}
                                                    width={300}
                                                    height={300}
                                                    alt="Product image."
                                                />)}
                                            </div>
                                        </div>
                                    </div>
                                    <div className="row mt-3 d-none d-lg-block">
                                        <div className="col-12 d-flex justify-content-center">
                                            {colorList && colorList.map((e) => {
                                                return (
                                                    <div
                                                        key={e.color}
                                                        style={{ width: 60 }}
                                                        className="me-2 ratio ratio-1x1"
                                                    >
                                                        {e.url && <img
                                                            className="rounded"
                                                            src={e.url}
                                                            width={60}
                                                            height={60}
                                                            alt="Product image."
                                                        />}
                                                    </div>
                                                );
                                            })}
                                        </div>
                                    </div>
                                </div>

                                <div className="col-lg-7">
                                    <div className="d-flex">
                                        <Field className="form-control d-inline h2 mb-0 fw-semibold me-3" name="productName" type="text"
                                            placeholder="Product name"
                                            value={initialProduct.productName}
                                            onChange={e => setProduct({ ...initialProduct, productName: e.target.value })} />
                                        {/* <div className="d-inline h2 mb-0 fw-semibold me-3">
                                            Product name here
                                        </div> */}
                                    </div>

                                    <div className="vstack">
                                        {/* <h4 className="fw-semibold">15000Ks</h4> */}
                                        <div className="d-flex align-items-center">
                                            <Field className="form-control fw-semibold my-2" name="price" type="number"
                                                placeholder="Product price"
                                                value={initialProduct.price}
                                                onChange={e => setProduct({ ...initialProduct, price: e.target.value })}
                                            /> VND
                                        </div>
                                        {/* <p className="fw-light">
                                            Lorem ipsum is placeholder text commonly used in the graphic,
                                            print, and publishing industries for previewing layouts and
                                            visual mockups.
                                        </p> */}
                                        <Field className="form-control fw-light my-2"
                                            value={initialProduct.description}
                                            onChange={e => setProduct({ ...initialProduct, description: e.target.value })}
                                            name="description" as="textarea" type="text"
                                            placeholder="Description" />
                                        <h4 className="fw-semibold">Product attributes:</h4>
                                        <div>
                                            <select name="selectedAttr" value={selectedAttr} onChange={onSelectedAttrChanged}>
                                                <option disabled value="">Select attribute to add</option>
                                                {attributeList.map(attribute => (
                                                    (<option key={attribute.attributeId} value={attribute.attributeId}>
                                                        {attribute.attributeName}
                                                    </option>)
                                                ))}
                                            </select>
                                            <button className="ms-3 btn btn-secondary" onClick={onAddAttr} type="button">
                                                Add attribute
                                            </button>
                                        </div>
                                        <dl className="row mb-0">
                                            {initialProduct.attributes && initialProduct.attributes.map(attribute => (
                                                <div key={attribute.attributeId}>
                                                    <div className="d-flex my-2">
                                                        <dt className="col-sm-3 fw-semibold">{attribute.attributeName}</dt>
                                                        <span type="button" className="btn btn-danger" onClick={() => handleRemoveAtt(attribute.attributeId)}>Delete</span>
                                                    </div>

                                                    <Field
                                                        className="form-control col-sm-9"
                                                        type="text"
                                                        name={`attributesMap.${attribute.attributeId}`}
                                                        value={attribute.value}
                                                        onChange={e => {
                                                            const attributes = initialProduct.attributes.filter(at => at.attributeId !== attribute.attributeId)
                                                            setProduct({
                                                                ...initialProduct, attributes: [...attributes,
                                                                {
                                                                    attributeId: attribute.attributeId,
                                                                    attributeName: attribute.attributeName,
                                                                    value: e.target.value
                                                                }]
                                                            })
                                                        }
                                                        }
                                                    />
                                                </div>
                                            ))}
                                            {/* <dt className="col-sm-3 fw-semibold">Code#</dt>
                                            <dd className="col-sm-9">10001</dd>
                                            <dt className="col-sm-3 fw-semibold">Category</dt>
                                            <dd className="col-sm-9">Electronics</dd>
                                            <dt className="col-sm-3 fw-semibold">Delivery</dt>
                                            <dd className="col-sm-9">Yangon, Mandalay</dd> */}
                                        </dl>
                                        <hr className="text-muted" />
                                        <div>
                                            Category
                                            <Field as="select" name="categoryId" className="form-control col-sm-3 fw-semibold" onChange={e => setProduct({ ...initialProduct, categoryId: e.target.value })} >
                                                <option disabled value="">Select Category to add</option><></>
                                                {categoryList.map(c =>
                                                    (<option key={c.categoryId} value={c.categoryId}>{c.categoryName} </option>)
                                                )}
                                            </Field>
                                        </div>
                                        <dl className="row gy-2 mb-4">
                                            <dt className="col-12 fw-semibold">Color:</dt>
                                            <div className="col-3 fw-semibold d-flex">
                                                <input className="form-control" type="text" value={currentColor} name="currentColor"
                                                    onChange={(e) => setCurrentColor(e.target.value)} />
                                                <button className="ms-3 btn btn-secondary" onClick={onAddColor} type="button">
                                                    Add color
                                                </button>
                                            </div>
                                            <dd className="col-12">
                                                {/* <div className="hstack gap-2"> */}
                                                {colorList && colorList.map(c => (
                                                    <div key={c.color} className="form-check d-block my-3">
                                                        <span type="button" className="btn btn-danger" onClick={() => handleRemoveColor(c.color)}>Delete</span>
                                                        <input
                                                            type="radio"
                                                            className="form-check-input"
                                                            name="selectedColor"
                                                            id={c.color}
                                                            value={c.color}
                                                            onChange={e => setSelectedColor(e.target.value)}
                                                        />
                                                        <label
                                                            className="form-check-label fw-medium mx-2"
                                                            htmlFor={c.color}
                                                        >
                                                            {c.color}
                                                        </label>
                                                        <input className="d-none" type="file" id={c.color + 'image'} name={c.color + 'image'}
                                                            onChange={(e) => onFileSelected(e, c.color)} />
                                                        {
                                                            c.url.length === 0 &&
                                                            (<label
                                                                className="btn btn-primary"
                                                                htmlFor={c.color + 'image'}
                                                            >
                                                                Upload image
                                                            </label>)
                                                        }

                                                    </div>
                                                ))}
                                                {/* </div> */}
                                            </dd>
                                        </dl>
                                        <button type="submit">Submit</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br />
                    <br />
                    <br />

                </Form>
            </Formik>
        </div>
    );
}

export default ProductForm;
