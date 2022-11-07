import Head from "next/head";
import Footer from "./footer";
import Header from "./header";

function AdminLayout({ children }) {
    return (
        <>
            {/* <Head>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="theme-color" content="#ffffff" />
        <meta name="description" content="Best e-commerce app" />
        <link rel="icon" href="/favicon.ico" />
        <title>Mocha Mart</title>
      </Head> */}
            <div className="d-flex flex-column h-100">
                <h2 className="d-flex text-center">Admin Page</h2>
                <main className="flex-shrink-0">{children}</main>
            </div>
        </>
    );
}

export default AdminLayout;