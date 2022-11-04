import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Link from "next/link";
import { useState, useEffect } from "react";
import { useRouter } from 'next/router'
import categoryService from "../services/category.service";


function Header({ simple, hideAuth }) {
  const router = useRouter()
  let title = process.env.APP_NAME;
  const [categorys, setCategorys] = useState([])

  useEffect(() => {
    categoryService.getCategoris().then(res => {
      setCategorys(res.data)
    })
  }, []);

  const handleSearch = () => {
    const productName = document.getElementById('search').value
    router.push({
      pathname: '/search',
      query: {
        productName: productName
      },
    })
    
  }

  return (
    <header>
      <nav className="navbar navbar-expand-lg navbar-light bg-white border-bottom">
        <div className="container">
          <Link href="/">
            <a className="navbar-brand">
              {/* <FontAwesomeIcon
                icon={["fas", "shopping-basket"]}
                className="d-inline-block"
              /> */}
              <span className="ms-2 mb-0 h4 text-dark fw-bold">
                Apple Store
              </span>
            </a>
          </Link>
          <div className="collapse navbar-collapse">
            <form className="d-flex w-75 p-3 h-50">
              <div className="input-group">
                <input
                  className="form-control"
                  type="search"
                  id="search"
                  placeholder="Search..."
                  aria-label="Search"
                  size="32"
                />
                <button type="button" className="btn btn-dark" onClick={handleSearch && handleSearch}>
                  <FontAwesomeIcon icon={["fas", "search"]} />
                </button>
              </div>
            </form>
          </div>
          <div className="d-flex">
            {!hideAuth && (
              <>
                <Link href="/auth/login">
                  <a className="btn btn-outline-dark d-none d-md-block">
                    Login
                  </a>
                </Link>
                <Link href="/auth/sign-up">
                  <a className="btn btn-dark d-none d-md-block ms-2">
                    Sign up
                  </a>
                </Link>
              </>
            )}
            <Link href="/shopping-cart">
              <a className="btn btn-light border position-relative ms-2 fw-normal">
                <FontAwesomeIcon icon={["fas", "shopping-cart"]} />
                &nbsp;Cart
                <span className="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger my-auto">
                  3
                </span>
              </a>
            </Link>
          </div>
        </div>
      </nav>
      {!simple && (
        <nav className="navbar navbar-expand-lg navbar-light bg-white border-bottom">
          <div className="container">
            <button
              className="navbar-toggler ms-auto"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#navbarNavDropdown"
              aria-controls="navbarNavDropdown"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarNavDropdown">
              <ul className="navbar-nav">
              {categorys.map(category => {
                return (
                  <li key={category.categoryId} className="nav-item">
                  <Link href={`/explore/${category.categoryId}`}>
                    <a className="nav-link h4 mx-3 text-uppercase font-weight-normal">{category.categoryName}</a>
                  </Link>
                </li>
                )
              })}
              </ul>
              <ul className="ms-auto navbar-nav">
                <li className="nav-item dropdown">
                  <a
                    href="#"
                    className="nav-link dropdown-toggle"
                    role="button"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                    id="languageMenuLink"
                  >
                    English
                  </a>
                  <ul
                    className="dropdown-menu dropdown-menu-macos dropdown-menu-end"
                    aria-labelledby="languageMenuLink"
                  >
                    <li>
                      <a href="#" className="dropdown-item">
                        English
                      </a>
                    </li>
                    <li>
                      <a href="#" className="dropdown-item mt-1">
                        Myanmar
                      </a>
                    </li>
                  </ul>
                </li>
              </ul>
            </div>
          </div>
        </nav>
      )}
    </header>
  );
}

export default Header;
