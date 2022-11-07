import ProductForm from "../../../components/product/product-form";
import { useEffect, useState } from 'react'
import { useRouter } from 'next/router'
import categoryService from "../../api/categoryService";
import attributeService from "../../api/attributeService";
import productService from "../../api/productService";
import { set } from "react-hook-form";

function AddProduct() {
    const[attributeList, setAtributeList] = useState([])
    const[categoryList, setCategoryList] = useState([])
    const[attributesMap, setAttributesMap] = useState({})
    const[product, setProduct] = useState({})
    const router = useRouter()
    const {
        query: { id },

    } = router
    const initialProduct = {
        productName: '',
        description: '',
        price: '',
        categoryId: '',
        attributes: [],
        attributesMap: attributesMap,
        images: []
    }
    useEffect(()=> {
        if(id){
            productService.getDetaiProduct(id)
            .then(res => setProduct({
                productName: res.data.productName,
                description: res.data.description,
                price: res.data.price,
                categoryId: res.data.categoryId,
                attributes: res.data.attributes,
                attributesMap: attributesMap,
                images: res.data.images
            }))
        }
        else{
            setProduct(initialProduct)
        }
    }, [id])
    console.log(product)
    useEffect(()=> {
        attributeService.getAttributes()
        .then(res => {
            setAtributeList(res.data);

            const attributes = {};
            res.data.forEach(a => {
                attributes[a.attributeId.toString()] = '';
            })
        
            setAttributesMap(attributes)
        })
    }, [])

    useEffect(()=> {
        categoryService.getCategorys()
        .then(res => setCategoryList(res.data))
    }, [])

    return (
        <ProductForm initialProduct={product} 
        update = {id}
        categoryList={categoryList}
        attributeList={attributeList}
        setProduct = {setProduct}
        ></ProductForm>
    );
}

export default AddProduct;